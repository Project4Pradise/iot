import request from '@/utils/request'

// 查询风障参数列表
export function listBarrier(query) {
  return request({
    url: '/config/barrier/list',
    method: 'get',
    params: query
  })
}

// 查询风障参数详细
export function getBarrier(id) {
  return request({
    url: '/config/barrier/' + id,
    method: 'get'
  })
}

// 新增风障参数
export function addBarrier(data) {
  return request({
    url: '/config/barrier',
    method: 'post',
    data: data
  })
}

// 修改风障参数
export function updateBarrier(data) {
  return request({
    url: '/config/barrier',
    method: 'put',
    data: data
  })
}

// 删除风障参数
export function delBarrier(id) {
  return request({
    url: '/config/barrier/' + id,
    method: 'delete'
  })

}
//发送mqtt消息
/*export function  sendmqtt(qos, retained, topic, pushMessage){
  console.log('qos:', qos);
  console.log('retained:', retained);
  console.log('topic:', topic);
  console.log('pushMessage:', pushMessage);
  return request({
    url:'/mqtt/publishTopic',
    method:"post",
    data: {
      qos: qos,
      retained: retained,
      topic: topic,
      pushMessage: pushMessage
    }
  })
}*/
export function sendmqtt(mqttMessage, topic) {
  return request({
    url: '/mqtt/publishTopic',
    method: 'post',
    params: {
      topic: topic
    },
    data: mqttMessage
  });
}
export function selectAreaList(){
  const url='/config/area/listAll';

  return request({
    url:url,
    method:'get',
  })

}

export function updateWorkingTrendsByIds(ids,WorkingTrend){
  const url = `/config/barrier//workingTrend?workingTrend=${WorkingTrend}&ids=${ids.join(',')}`;
  return request({
    url:url,
    method:'put'
  })
}
