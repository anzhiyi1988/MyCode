str_list = [
    # "交期变更说明：->文彬测试608, 期望交期变更类型：->需求原因-提前, 期望交付时间：2022-06-30->2022-06-29",
    # "产品：ACG>1-IaaS>DCC->, 交期变更说明：文彬测试608->测试607, 期望交付时间：2022-06-29->2022-12-12",
    # "预算审批时间：null->0001-01-01, 交期变更说明：测试607->文彬测试607, 期望交期变更类型：需求原因-提前->其他, 期望交付时间：2022-12-12->2022-06-30",
    # "交期变更说明：文彬测试607->文彬测试60701, 期望交期变更类型：其他->需求原因-提前, 期望交付时间：2022-06-30->2022-06-25",
    # "交期变更说明：文彬测试60701->文彬测试608, 期望交付时间：2022-06-25->2022-06-29",
    # "交期变更说明：文彬测试608->测试609, 期望交付时间：2022-06-29->2022-12-13",
    # "交期变更说明：测试609->测试验收, 期望交期变更类型：需求原因-提前->其他, 期望交付时间：2022-12-13->2022-06-30",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-07-15->2022-08-15",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-04-15->2022-08-15",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-04-15->2022-08-15",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-04-15->2022-08-15",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-07-15->2022-08-15",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-05-15->2022-08-15",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-04-15->2022-08-15",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-06-15->2022-08-15",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-05-15->2022-08-15",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-07-05->2022-08-15",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-07-05->2022-08-15",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-07-05->2022-08-15",
    # "期望交付时间：2022-08-15->2022-09-15",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-06-14->2022-10-15",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-06-14->2022-11-15",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-06-14->2022-12-15",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-08-15->2022-10-15",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-08-15->2022-11-15",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-08-15->2022-12-15",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-05-13->2022-10-15",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-05-31->2022-11-15",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-05-31->2022-12-15",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-07-30->2022-09-30",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-07-30->2022-11-30",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-10-10->2022-12-10",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-07-06->2022-08-04",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-07-01->2022-10-01",
    # "期望交付时间：2022-09-15->2022-10-15",
    # "期望交付时间：2022-09-15->2022-07-01",
    # "期望交付时间：2022-07-01->2022-08-15",
    # "期望交付时间：2022-09-15->2022-07-01",
    # "期望交付时间：2022-09-15->2022-08-15",
    # "期望交付时间：2022-09-15->2022-08-15",
    # "期望交付时间：2022-09-15->2022-08-01",
    # "期望交付时间：2022-07-01->2022-07-15",
    # "期望交付时间：2022-09-15->2022-08-01",
    # "期望交付时间：2022-09-15->2022-10-15",
    # "期望交付时间：2022-09-15->2022-07-15",
    # "期望交付时间：2022-09-15->2022-08-15",
    # "期望交付时间：2022-09-15->2022-08-01",
    # "期望交付时间：2022-09-15->2022-08-15",
    # "期望交付时间：2022-09-15->2022-08-15",
    # "期望交付时间：2022-09-15->2022-08-15",
    # "期望交付时间：2022-09-15->2022-08-15",
    # "期望交付时间：2022-09-15->2022-08-15",
    # "期望交付时间：2022-09-15->2022-08-15",
    # "期望交付时间：2022-09-15->2022-08-15",
    # "期望交付时间：2022-09-15->2022-08-15",
    # "期望交付时间：2022-09-15->2022-07-15",
    # "期望交付时间：2022-09-15->2022-07-15",
    # "期望交期变更类型：->供应原因-延后, 期望交付时间：2022-08-15->2022-08-05",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-08-15->2022-09-05",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-08-15->2022-10-05",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-05-15->2022-08-05",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-05-15->2022-09-05",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-05-15->2022-10-05",
    # "预算审批时间：null->0001-01-01, 期望交期变更类型：->需求原因-延后, 期望交付时间：2022-08-15->2022-09-15",
    # "期望交付时间：2022-08-04->2022-09-15",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-08-10->2022-09-15",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-10-10->2022-12-10",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-11-15->2022-12-10",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-09-30->2022-12-10",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-09-30->2022-12-10",
    # "期望交付时间：2022-07-21->2022-06-30",
    # "预算审批时间：null->0001-01-01, 期望交期变更类型：->需求原因-提前, 期望交付时间：2022-09-10->2022-08-15",
    # "预算审批时间：null->0001-01-01, 期望交期变更类型：->新品导入, 期望交付时间：2022-06-30->2022-07-01",
    # "预算审批时间：null->0001-01-01, 期望交期变更类型：->新品导入, 期望交付时间：2022-06-30->2022-07-01",
    # "预算审批时间：null->0001-01-01, 期望交期变更类型：->新品导入, 期望交付时间：2022-06-30->2022-07-01",
    # "预算审批时间：null->0001-01-01, 期望交期变更类型：->新品导入, 期望交付时间：2022-06-30->2022-07-01",
    # "预算审批时间：null->0001-01-01, 期望交期变更类型：->新品导入, 期望交付时间：2022-06-30->2022-07-01",
    # "期望交付时间：2022-08-15->2022-06-30",
    # "期望交付时间：2022-08-15->2022-09-15",
    # "期望交付时间：2022-08-15->2022-09-15",
    # "期望交付时间：2022-08-15->2022-09-15",
    # "期望交付时间：2022-08-15->2022-07-15",
    # "期望交付时间：2022-08-15->2022-07-15",
    # "期望交付时间：2022-08-15->2022-07-15",
    # "期望交付时间：2022-08-01->2022-07-15",
    # "期望交付时间：2022-06-01->2022-07-01",
    # "期望交付时间：2022-08-15->2022-07-15",
    # "期望交付时间：2022-06-01->2022-07-01",
    # "期望交付时间：2022-06-01->2022-07-01",
    # "期望交付时间：2022-06-01->2022-07-01",
    # "期望交付时间：2022-06-01->2022-07-01",
    # "期望交付时间：2022-06-30->2022-08-30",
    # "期望交付时间：2022-08-01->2022-08-15",
    # "期望交付时间：2022-08-01->2022-08-15",
    # "期望交付时间：2022-08-01->2022-08-15",
    # "期望交付时间：2022-08-01->2022-08-15",
    # "期望交付时间：2022-08-01->2022-08-15",
    # "期望交付时间：2022-06-30->2022-08-15",
    # "期望交付时间：2022-08-15->2022-09-15",
    # "期望交付时间：2022-08-15->2022-09-15",
    # "期望交付时间：2022-07-01->2022-09-15",
    # "期望交付时间：2022-08-15->2022-09-01",
    # "期望交付时间：2022-08-15->2022-09-01",
    # "期望交付时间：2022-07-15->2022-10-15",
    # "期望交付时间：2022-08-15->2022-09-01",
    # "期望交付时间：2022-12-15->2022-10-15",
    # "期望交付时间：2022-10-15->2022-11-01",
    # "期望交付时间：2022-10-15->2022-11-01",
    # "期望交付时间：2022-10-15->2022-11-01",
    # "期望交付时间：2022-12-31->2022-12-01",
    # "期望交付时间：2022-10-01->2022-12-01",
    # "期望交付时间：2022-12-31->2022-11-01",
    # "期望交付时间：2022-11-01->2022-12-31",
    # "期望交付时间：2022-12-01->2022-12-31",
    # "期望交付时间：2022-06-25->2022-07-10",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-06-15->2022-07-10",
    # "交期变更说明：->协商时间10月15日, 期望交期变更类型：->其他, 期望交付时间：2022-06-30->2022-10-15",
    # "期望交期变更类型：->需求原因-延后, 期望交付时间：2022-06-15->2022-07-10",
    # "交期变更说明：->协商时间7月15日, 期望交期变更类型：->其他, 期望交付时间：2022-05-31->2022-07-15",
    # "期望交付时间：2022-05-15->2022-09-15",
    # "期望交付时间：2022-10-10->2022-12-10",
    # "期望交付时间：2022-10-10->2022-12-10",
    # "期望交付时间：2022-09-10->2022-12-10",
    # "期望交付时间：2022-11-10->2022-12-10",
    # "期望交付时间：2022-11-10->2022-12-10",
    # "预算审批时间：0001-01-01->2022-05-30, 期望交期变更类型：->需求原因-延后, 期望交付时间：2022-07-01->2022-07-04",
    # "预算审批时间：0001-01-01->2022-05-30, 期望交期变更类型：->需求原因-延后, 期望交付时间：2022-07-01->2022-07-04",
    # "预算审批时间：0001-01-01->2022-05-30, 期望交期变更类型：->需求原因-延后, 期望交付时间：2022-07-01->2022-07-04",
    # "预算审批时间：0001-01-01->2022-05-30, 期望交期变更类型：->需求原因-延后, 期望交付时间：2022-07-01->2022-07-04",
    # "预算审批时间：0001-01-01->2022-05-30, 期望交期变更类型：->需求原因-延后, 期望交付时间：2022-07-01->2022-07-04",
    # "预算审批时间：null->0001-01-01, 交期变更说明：->询价单跨标段重新获取新标段价格, 期望交期变更类型：->其他, 期望交付时间：2022-06-27->2022-07-05",
    "预算审批时间：null->0001-01-01, 交期变更说明：->询价单跨标段重新获取新标段价格,, 期望交期变更类型：->其他, 期望交付时间：2022-02-21->2022-07-05"
    # "期望交付时间：2022-06-29->2022-08-10",
    # "期望交付时间：2022-06-29->2022-09-10",
    # "期望交付时间：2022-06-29->2022-10-15",
    # "期望交付时间：2022-06-29->2022-09-10",
    # "期望交付时间：2022-02-28->2022-10-15",
    # "期望交付时间：2022-06-29->2022-10-10",
    # "期望交付时间：2022-06-29->2022-10-10",
    # "期望交付时间：2022-06-29->2022-10-15",
    # "期望交付时间：2022-06-29->2022-10-10",
    # "期望交付时间：2022-06-29->2022-07-25",
    # "期望交付时间：2022-06-29->2022-08-10",
    # "期望交付时间：2022-06-29->2022-09-10",
    # "期望交付时间：2022-06-29->2022-10-10"
]

"""
复杂数据的拆分
"""
r_list = []
for s in str_list:
    item_list = s.split(',')
    for item in item_list:
        if item.find('：') >= 0 & item.find('->') >= 0:
            r = {
                'col_name': item.split('：')[0],
                'col_old_value': item.split('：')[1].split('->')[0],
                'col_new_value': item.split('：')[1].split('->')[1]
            }
            r_list.append(r)
print(r_list)