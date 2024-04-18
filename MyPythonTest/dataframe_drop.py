#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
测试一下删数据
"""

import pandas as pd


def del_data(df: pd.DataFrame):
    print(df)
    inds = df.tail(3).index
    print(inds)
    ndf = df.drop(inds, axis=0)
    print(ndf)


json = [
    {'col1': '1', 'col2': 'b'},
    {'col1': '2', 'col2': 'b'},
    {'col1': '3', 'col2': 'b'},
    {'col1': '4', 'col2': 'b'},
    {'col1': '5', 'col2': 'b'},
    {'col1': '6', 'col2': 'b'},
    {'col1': '7', 'col2': 'b'}
]
data = pd.DataFrame(json)
del_data(data)
