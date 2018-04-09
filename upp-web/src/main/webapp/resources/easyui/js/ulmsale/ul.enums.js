/**
 * Created by liming on 16/6/21.
 */
//枚举类集合
var sysEnums = {

    //角色等级
    gradeType: {A: '一级', B: '二级', C: '三级'},

    //角色状态
    statusType: {NORMAL: '开启', CLOSE: '关闭'},

    roleStatusType: {'CLOSE': '禁用', 'NORMAL': '启用', 'INITIAL': '初次登入'},

    userType: {'00': '代理商操作员', '01': '商户操作员', '99': '机构运营操作员'},

    transType: {'QQ': 'QQ', 'JD': '京东', 'WECHAT': '微信'},

    transStatus: {'SUCCESS': '成功', 'FAIL': '失败', 'PROCESSING': '处理中', 'ORDER_EXPIRE': '超时未支付', 'INITIAL': '初始待确认'}
}


