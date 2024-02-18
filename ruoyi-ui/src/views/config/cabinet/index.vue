<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择">
          <el-option
            v-for="item in typeOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['config:cabinet:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['config:cabinet:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['config:cabinet:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['config:cabinet:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="cabinetList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"  />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="名称" align="center" prop="name" />
      <el-table-column label="类型" align="center" prop="type" >
        <template slot-scope="scope">
          <el-tag type="primary" v-if="scope.row.type==1">通讯柜</el-tag>
          <el-tag type="warning" v-if="scope.row.type==2">控制柜</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-s-order"
            @click="handleDeatil(scope.row)"
          >查看详情</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['config:cabinet:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['config:cabinet:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改柜管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择">
            <el-option
              v-for="item in typeOptions1"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
<!--    查看详情对话框-->
    <el-dialog title="柜详情" :visible.sync="dialogTableVisible"  fullscreen=true>
      <el-col :span="20" :xs="24" >
        <h2>故障详情</h2>
        <el-table :data="cabinetErrors">
          <el-table-column label="id" align="center" prop="id" />
          <el-table-column label="名称" align="center" prop="name" />
          <el-table-column label="错误类型" align="center" prop="errorId" />
          <el-table-column label="故障风障Id" align="center" prop="barrierId" />
          <el-table-column label="描述" align="center" prop="des"  />
          <el-table-column label="图片" align="center" prop="imgURL"  />
        </el-table>
        <h2>风障详情</h2>
        <el-table :data="cabinetDeailList">
          <el-table-column label="id" align="center" prop="id" />
          <el-table-column label="名称" align="center" prop="name" />
          <el-table-column label="当前电流" align="center" prop="currentCurrent" />
          <el-table-column label="运行高度" align="center" prop="currentHeight" />
          <el-table-column label="分组id" align="center" prop="groupId" />
          <el-table-column label="区域Id" align="center" prop="areaId"  />
          <el-table-column prop="workingTrend" label="工作趋势" align="center">
            <template slot-scope="scope">
              <el-tag type="primary" v-if="scope.row.workingTrend==0">停止</el-tag>
              <el-tag type="warning" v-if="scope.row.workingTrend==1">上升</el-tag>
              <el-tag type="success" v-if="scope.row.workingTrend==2">下降</el-tag>
              <el-tag type="info" v-if="scope.row.workingTrend==3">上升到位</el-tag>
              <el-tag type="success" v-if="scope.row.workingTrend==4">下降到位</el-tag>
            </template>
          </el-table-column>
          <el-table-column label="是否工作"  >
            <template slot-scope="scope" >
              <el-switch v-model="scope.row.isWorking" active-color="#13ce66" inactive-color="#ccc"   :disabled="true" ></el-switch>
            </template>
          </el-table-column>
          <el-table-column label="是否重试"  >
            <template slot-scope="scope" >
              <el-switch v-model="scope.row.isRetrying" active-color="#13ce66" inactive-color="#ccc"  :disabled="true" ></el-switch>
            </template>
          </el-table-column>

        </el-table>
      </el-col>
      <el-col :span="4" :xs="24">

        <el-card style="height: 100%;">
          <div slot="header">
            <span>日志</span>
            <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
          </div>
          <div style="overflow-y: auto; max-height: 300px;">
            <div v-for="o in 500" :key="o">{{'日志 ' + o }}</div>
          </div>
        </el-card>
      </el-col>


    </el-dialog>

  </div>
</template>

<script>
import { listCabinet, getCabinet, delCabinet, addCabinet, updateCabinet,selectDetailByControlId,selectDetailByCommunicationId ,selectErrorsByCabinetId} from "@/api/config/cabinet";

export default {
  name: "Cabinet",
  data() {
    return {
      typeOptions:[
        {
          value: '1',
          label: '通讯柜'
        }, {
          value: '2',
          label: '控制柜'
        }
      ],
      typeOptions1:[
        {
          value: '1',
          label: '通讯柜'
        }, {
          value: '2',
          label: '控制柜'
        }
      ],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 柜管理表格数据
      cabinetList: [],
      //查看详情数据
      cabinetDeailList:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      dialogTableVisible:false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        type: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      cabinetErrors:[]
    };
  },
  created() {
    this.getList();
  },
  methods: {
    handleDeatil(row){
      this.dialogTableVisible=true;
      if(row.type==1){
        selectDetailByCommunicationId(row.id).then(res=>{
          this.cabinetDeailList=res.data;

        });
        selectErrorsByCabinetId(row.id).then(res=>{
          this.cabinetErrors=res.data;
        });
      }else if(row.type==2){
        selectDetailByControlId(row.id).then(res=>{
          this.cabinetDeailList=res.data;
        });
        selectErrorsByCabinetId(row.id).then(res=>{
          this.cabinetErrors=res.data;
        });
      }
    },
    /** 查询柜管理列表 */
    getList() {
      this.loading = true;
      listCabinet(this.queryParams).then(response => {
        this.cabinetList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        name: null,
        type: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加柜管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCabinet(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改柜管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCabinet(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCabinet(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除柜管理编号为"' + ids + '"的数据项？').then(function() {
        return delCabinet(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('config/cabinet/export', {
        ...this.queryParams
      }, `cabinet_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
