<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="设备名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入设备名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属分组id" prop="GroupId">
        <el-input
          v-model="queryParams.GroupId"
          placeholder="请输入所属分组id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="区域Id" prop="AreaId">
        <el-select v-model="queryParams.AreaId" placeholder="请选择">
          <el-option
            v-for="item in areaOptionsup"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="通信柜id" prop="CommunicationCabinetId">
        <el-input
          v-model="queryParams.CommunicationCabinetId"
          placeholder="请输入所属的通信柜id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="控制柜id" prop="ControlCabinetId">
        <el-input
          v-model="queryParams.ControlCabinetId"
          placeholder="请输入所属的控制柜id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否工作" prop="isWorking">
        <el-select v-model="queryParams.isWorking" placeholder="请选择">
          <el-option
            v-for="item in stateOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="是否重试" prop="isRetrying">

        <el-select v-model="queryParams.isRetrying" placeholder="请选择">
          <el-option
            v-for="item in stateOptions"
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


    <el-col :span="1.5">
      <el-button
        type="primary"
        plain
        icon="el-icon-download"
        size="mini"
        @click="handleBarrierUp"
        v-hasPermi="['config:barrier:edit']"
      >升障</el-button>
    </el-col>
    <el-col :span="1.5">
      <el-button
        type="danger"
        plain
        icon="el-icon-download"
        size="mini"
        @click="handleBarrierDown"
        v-hasPermi="['config:barrier:edit']"
      >降障</el-button>
    </el-col>
    <el-col :span="1.5">
      <el-button
        type="info"
        plain
        icon="el-icon-download"
        size="mini"
        @click="handleBarrierStop"
        v-hasPermi="['config:barrier:edit']"
      >停止</el-button>
    </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['config:barrier:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>


    <el-table v-loading="loading" :data="barrierList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="名称" align="center" prop="name" />
      <el-table-column label="运行高度" align="center" prop="currentHeight" />
      <el-table-column label="分组id" align="center" prop="groupId" />
      <el-table-column label="区域Id" align="center" prop="areaId"  />
      <el-table-column label="通信柜id" align="center" prop="communicationCabinetId" />
      <el-table-column label="控制柜id" align="center" prop="controlCabinetId" />
      <el-table-column prop="workingTrend" label="工作趋势">
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
      <el-table-column label="升障电流阈值" align="center" prop="raiseBarrierCurrentThreshold" />
      <el-table-column label="降障电流阈值" align="center" prop="fallBarrierCurrentThreshold" />
      <el-table-column label="升障时间阈值" align="center" prop="raiseBarrierTimeThreshold" />
      <el-table-column label="降障时间阈值" align="center" prop="fallBarrierTimeThreshold" />
      <el-table-column label="最大重试次数" align="center" prop="retryCount" />
<!--   注意：   重试电流阈值大于电流阈值-->
      <el-table-column label="重试升障电流阈值" align="center" prop="raiseBarrierRetryCurrentThreshold" />
      <el-table-column label="重试降障电流阈值" align="center" prop="fallBarrierRetryCurrentThreshold" />

      <el-table-column label="重试升障时间阈值" align="center" prop="raiseBarrierRetryTimeThreshold" />
      <el-table-column label="重试降障时间阈值" align="center" prop="fallBarrierRetryTimeThreshold" />

    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改风障参数对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form"  label-width="80px">
        <el-form-item label="设备名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入设备名称" />
        </el-form-item>
        <el-form-item label="分组id" prop="GroupId">
          <el-input v-model="form.groupId" placeholder="请输入分组id" />
        </el-form-item>
        <el-form-item label="区域Id" prop="AreaId">
          <el-select v-model="form.areaId" placeholder="请选择">
            <el-option
              v-for="item in areaOptionsup"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="通信柜id" prop="CommunicationCabinetId">
          <el-input v-model="form.communicationCabinetId" placeholder="请输入通信柜id" />
        </el-form-item>
        <el-form-item label="控制柜id" prop="ControlCabinetId">
          <el-input v-model="form.controlCabinetId" placeholder="请输入控制柜id" />
        </el-form-item>
        <el-form-item label="升障电流阈值" prop="RaiseBarrierCurrentThreshold">
          <el-input v-model="form.raiseBarrierCurrentThreshold" placeholder="请输入升障电流阈值" />
        </el-form-item>
        <el-form-item label="降障电流阈值" prop="FallBarrierCurrentThreshold">
          <el-input v-model="form.fallBarrierCurrentThreshold" placeholder="请输入降障电流阈值" />
        </el-form-item>
        <el-form-item label="升障时间阈值" prop="RaiseBarrierTimeThreshold">
          <el-input v-model="form.raiseBarrierTimeThreshold" placeholder="请输入升障时间阈值" />
        </el-form-item>
        <el-form-item label="降障时间阈值" prop="FallBarrierTimeThreshold">
          <el-input v-model="form.fallBarrierTimeThreshold" placeholder="请输入降障时间阈值" />
        </el-form-item>
        <el-form-item label="重试次数" prop="RetryCount">
          <el-input v-model="form.retryCount" placeholder="请输入重试次数" />
        </el-form-item>
        <el-form-item label="重试升障电流阈值" prop="RaiseBarrierRetryCurrentThreshold">
          <el-input v-model="form.raiseBarrierRetryCurrentThreshold" placeholder="请输入重试升障电流阈值(重试电流阈值大于电流阈值)" />
        </el-form-item>
        <el-form-item label="重试降障电流阈值" prop="FallBarrierRetryCurrentThreshold">
          <el-input v-model="form.fallBarrierRetryCurrentThreshold" placeholder="请输入重试降障电流阈值(重试电流阈值大于电流阈值)" />
        </el-form-item>
        <el-form-item label="重试升障时间时间阈值" prop="RaiseBarrierRetryTimeThreshold">
          <el-input v-model="form.raiseBarrierRetryTimeThreshold" placeholder="请输入重试升障时间阈值" />
        </el-form-item>
        <el-form-item label="重试降障时间时间阈值" prop="FallBarrierRetryTimeThreshold">
          <el-input v-model="form.fallBarrierRetryTimeThreshold" placeholder="请输入重试降障时间阈值" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listBarrier, getBarrier, delBarrier, addBarrier, updateBarrier,updateWorkingTrendsByIds,sendmqtt} from "@/api/config/barrier";


export default {
  name: "remote",
  data() {
    return {
      areaName:undefined,
      areaOPtions:undefined,

      logicBarrier:{},
      sendmsg:{
        id:null,
        workingTrend:null
      },
      mqttMsg:{
        qos:null,
        retained:null,
        payload:null
      },
      stateOptions:[
        {
          value: 'false',
          label: '否'
        }, {
          value: 'true',
          label: '是'
        }
      ],
      areaOptionsup:[
        {
          value: '1',
          label: '1'
        }, {
          value: '2',
          label: '2'
        }, {
          value: '3',
          label: '3'
        }, {
          value: '4',
          label: '4'
        }
      ],
      groups:[],
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
      // 风障参数表格数据
      barrierList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      defaultProps: {
        children: "children",
        label: "label"
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        CurrentHeight: null,
        GroupId: null,
        AreaId: null,
        CommunicationCabinetId: null,
        ControlCabinetId: null,
        WorkingTrends:null,
        isWorking: null,
        isRetrying: null,
        RaiseBarrierCurrentThreshold: null,
        FallBarrierCurrentThreshold: null,
        RaiseBarrierTimeThreshold: null,
        FallBarrierTimeThreshold: null,
        RetryCount: null,
        RaiseBarrierRetryCurrentThreshold: null,
        FallBarrierRetryCurrentThreshold: null,
        RaiseBarrierRetryTimeThreshold: null,
        FallBarrierRetryTimeThreshold: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "设备名称不能为空", trigger: "blur" }
        ],
        CurrentHeight: [
          { required: true, message: "运行高度不能为空", trigger: "blur" }
        ],
        AreaId: [
          { required: true, message: "区域Id不能为空", trigger: "blur" }
        ],
        isWorking: [
          { required: true, message: "是否工作不能为空", trigger: "blur" }
        ],
        isRetrying: [
          { required: true, message: "是否重试不能为空", trigger: "blur" }
        ],
        RaiseBarrierCurrentThreshold: [
          { required: true, message: "升障电流阈值", trigger: "blur" }
        ],
        FallBarrierCurrentThreshold: [
          { required: true, message: "降障电流阈值", trigger: "blur" }
        ],
        RaiseBarrierTimeThreshold: [
          { required: true, message: "升障时间阈值", trigger: "blur" }
        ],
        FallBarrierTimeThreshold: [
          { required: true, message: "降障时间阈值", trigger: "blur" }
        ],
        RetryCount: [
          { required: true, message: "重试次数", trigger: "blur" }
        ],
        RaiseBarrierRetryCurrentThreshold: [
          { required: true, message: "重试升障电流阈值（重试电流阈值大于电流阈值不能为空）", trigger: "blur" }
        ],
        FallBarrierRetryCurrentThreshold: [
          { required: true, message: "重试降障电流阈值（重试电流阈值大于电流阈值不能为空）", trigger: "blur" }
        ],
        RaiseBarrierRetryTimeThreshold: [
          { required: true, message: "重试升障时间阈值", trigger: "blur" }
        ],
        FallBarrierRetryTimeThreshold: [
          { required: true, message: "重试降障时间阈值", trigger: "blur" }
        ]
      }
    };
  },
  watch:{
    areaName(val){
      this.$refs.tree.filter(val);
    }
  },
  created() {
    this.getList();




  },
  methods: {

    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    /** 查询风障参数列表 */
    getList() {

      this.loading = true;
      listBarrier(this.queryParams).then(response => {
        this.barrierList = response.rows;
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
        CurrentHeight: null,
        GroupId: null,
        AreaId: null,
        CommunicationCabinetId: null,
        ControlCabinetId: null,
        isWorking: null,
        isRetrying: null,
        RaiseBarrierCurrentThreshold: null,
        FallBarrierCurrentThreshold: null,
        RaiseBarrierTimeThreshold: null,
        FallBarrierTimeThreshold: null,
        RetryCount: null,
        RaiseBarrierRetryCurrentThreshold: null,
        FallBarrierRetryCurrentThreshold: null,
        RaiseBarrierRetryTimeThreshold: null,
        FallBarrierRetryTimeThreshold: null
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
      this.title = "添加风障参数";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getBarrier(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改风障参数";
      });
    },


    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateBarrier(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBarrier(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除风障参数编号为"' + ids + '"的数据项？').then(function() {
        return delBarrier(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    //运动趋势批量修改
    handleBarrierUp(row){
      const ids = row.id || this.ids;
      const promises = [];

      for (let i = 0; i < ids.length; i++) {
        promises.push(
          getBarrier(ids[i]).then(res => {
            this.logicBarrier = res.data;
            if (this.logicBarrier.isWorking === false||this.logicBarrier.isRetrying===true) {

              throw new Error("选中的风障有正在重试或正在停止工作，请重新选择或更改风障状态");
            }
          })
        );
      }

      Promise.all(promises)
        .then(() => updateWorkingTrendsByIds(ids, 1))
        .then(() => {
          this.$modal.msgSuccess("升障成功");
          for (let i = 0; i < ids.length; i++) {
            this.sendmsg.id=ids[i];
            this.sendmsg.workingTrend=1;
            const jsonStr=JSON.stringify(this.sendmsg);
            const base64=btoa(jsonStr);
            this.mqttMsg.qos=1;
            this.mqttMsg.retained=false;
            this.mqttMsg.payload=base64;
            sendmqtt(this.mqttMsg,"iot/barrier");

          }
          this.getList();
        })
        .catch(error => {
          console.error("Error:", error);
          this.$modal.msgError("升障失败：" + error.message);
        });
    },

    handleBarrierDown(row) {
      const ids = row.id || this.ids;
      const promises = [];
      for (let i = 0; i < ids.length; i++) {
        promises.push(
          getBarrier(ids[i]).then(res => {
            this.logicBarrier = res.data;
            if (this.logicBarrier.isWorking === false||this.logicBarrier.isRetrying===true) {

              throw new Error("选中的风障有正在重试或正在停止工作，请重新选择或更改风障状态");
            }
          })
        );
      }

      Promise.all(promises)
        .then(() => updateWorkingTrendsByIds(ids, 2))
        .then(() => {
          this.$modal.msgSuccess("降障成功");
          for (let i = 0; i < ids.length; i++) {
            this.sendmsg.id=ids[i];
            this.sendmsg.workingTrend=2;
            const jsonStr=JSON.stringify(this.sendmsg);
            const base64=btoa(jsonStr);
            this.mqttMsg.qos=1;
            this.mqttMsg.retained=false;
            this.mqttMsg.payload=base64;
            sendmqtt(this.mqttMsg,"iot/barrier");

          }
          this.getList();
        })
        .catch(error => {
          console.error("Error:", error);
          this.$modal.msgError("降障失败：" + error.message);
        });
    },
    handleBarrierStop(row){
      const ids = row.id || this.ids;
      const promises = [];
      for (let i = 0; i < ids.length; i++) {
        promises.push(
          getBarrier(ids[i]).then(res => {
            this.logicBarrier = res.data;
            if (this.logicBarrier.isWorking === false||this.logicBarrier.isRetrying===true) {

              throw new Error("选中的风障有正在重试或正在停止工作，请重新选择或更改风障状态");
            }
          })
        );
      }

      Promise.all(promises)
        .then(() => updateWorkingTrendsByIds(ids, 0))
        .then(() => {
          this.$modal.msgSuccess("停止成功");
          for (let i = 0; i < ids.length; i++) {
            this.sendmsg.id=ids[i];
            this.sendmsg.workingTrend=0;
            const jsonStr=JSON.stringify(this.sendmsg);
            const base64=btoa(jsonStr);
            this.mqttMsg.qos=1;
            this.mqttMsg.retained=false;
            this.mqttMsg.payload=base64;
            sendmqtt(this.mqttMsg,"iot/barrier");

          }
          this.getList();
        })
        .catch(error => {
          console.error("Error:", error);
          this.$modal.msgError("停止失败：" + error.message);
        });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('config/barrier/export', {
        ...this.queryParams
      }, `barrier_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
