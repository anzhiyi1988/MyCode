# 通过正则表达式，在字符串中截取相应的内容

import re

ITEM_LIST = [
    'SBX-XI6-100D-E657-7个节点/柜，40A，46U-顺诠科技-31087',
    'SBX-XI6-100D-E394-7个节点/柜，40A，46U-顺诠科技-30744',
    'SBX-XI6-100D-E498-7个节点/柜，40A-online，46U-顺诠科技-30556',
    'SBX-XI6-100D-E394-7个节点/柜，40A，46U-H3C-30156',
    'SBX-XI6-100D-E395-7个节点/柜，40A，46U-H3C-30165',
    'SBX-XI6-100D-E396-7个节点/柜，40A-online，46U-H3C-30168',
    'SBX-XI6-100D-E220-4个节点/柜，40A，46U-顺诠科技-29033',
    'SBX-XI6-100D-B486-7个节点/柜，40A-online，46U-H3C-26818',
    'SBX-XI6-100D-D927-7个节点/柜，40A-online，46U-H3C-28571',
    'SBX-XI6-100D-C648-7个节点/柜，40A-online，46U-顺诠科技-20165',
    'SBX-XI6-100D-D464-4个节点/柜，40A-online，46U-H3C-24067',
    'SBX-XI6-100D-D464-4个节点/柜，40A-online，46U-浪潮-24066',
    'SBX-XI6-100D-B486-7个节点/柜，40A-online，46U-H3C-21551',
    'SBX-XI6-100D-B486-7个节点/柜，40A-online，46U-H3C-15287',
    'MBX-XI5-25D-B788-19个节点/柜，40A-online，46U-顺诠科技-17803',
    'MBX-XI5-25D-B832-17个节点/柜，40A-online，46U-顺诠科技-17817',
    'MBX-XI5-25D-B841-18个节点/柜，40A-online，46U-顺诠科技-17806',
    'MBX-XI5-25D-B843-33个节点/柜，40A-online，46U-顺诠科技-17809',
    'MBX-XI5-25D-B845-30个节点/柜，40A-online，46U-顺诠科技-17812',
    'MBX-XI5-25D-B846-33个节点/柜，40A-online，46U-顺诠科技-17815'
]

for item_name in ITEM_LIST:
    node_num = re.findall("-([0-9]+)个节点/柜", item_name)
    print(node_num)
    if len(node_num) == 1:
        node_num = node_num[0]
        print(node_num)
