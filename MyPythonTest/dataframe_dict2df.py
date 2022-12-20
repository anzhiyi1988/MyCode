#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
通过已有的python数据创建dataframe
"""

import pandas as pd

data = {
    "BDBL_split_公有云": {
        "BDBL302_split_B_split_CNN5(保定)": [
            [1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5],
            [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
            [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
            [2.5, 3.5, 4.5, 5.5, 6.5, 7.5, 8.5, 9.5, 10.5, 11.5, 12.5, 13.5]
        ],
        "BDBL304_split_B_split_CNN5(保定)": [
            [3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 3.0, 3.0],
            [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
            [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
            [4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0, 15.0]
        ],
        "BDBL401_split_B_split_CNN5(保定)": [
            [2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0],
            [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
            [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
            [3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0]
        ],
        "BDBL402_split_B_split_CNN5(保定)": [
            [3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5, 3.5],
            [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
            [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
            [4.5, 5.5, 6.5, 7.5, 8.5, 9.5, 10.5, 11.5, 12.5, 13.5, 14.5, 15.5]
        ]
    },
    "BDBL_split_公有云-25G": {
        "BDBL503_split_B_split_CNN5(保定)": [
            [2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0, 2.0],
            [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
            [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
            [3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0, 11.0, 12.0, 13.0, 14.0]
        ]
    }
}

month_list = pd.date_range('20220907'[:6] + '01', freq='MS', periods=12).strftime("%Y%m%d").tolist()

for idc_key, idc in data.items():
    idc_name = idc_key.split('_split_')[0]
    classify_name = idc_key.split('_split_')[1]
    for room_key, room in idc.items():
        room_name = room_key.split('_split_')[0]
        module_name = room_key.split('_split_')[1]
        region = room_key.split('_split_')[2]
        df = pd.DataFrame(
            room,
            index=['当前量', '在途量', '需求量', '预剩余'],
            columns=['month1', 'month2', 'month3', 'month4', 'month5', 'month6', 'month7', 'month8', 'month9',
                     'month10', 'month11', 'month12']
        ).reset_index().rename(columns={'index': 'stage'})
        df['idc_name'] = idc_name
        df['rack_classify_name'] = classify_name
        df['room_name'] = room_name
        df['module_name'] = module_name
        df['region'] = region
        print(df)
        print(df.columns)



