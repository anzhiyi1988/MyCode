#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
临时解决方案,给出excel测试数据
"""
import pandas as pd
import logging

logger = logging.getLogger()
logger.setLevel(logging.INFO)
formatter = logging.Formatter("[%(asctime)s] %(name)s:%(levelname)s: %(message)s:")
hdr = logging.StreamHandler()
hdr.setFormatter(formatter)
logger.addHandler(hdr)

excel = '/home/work/anzhiyi/baidu/ump/koadas/jobs/aaa.xlsx'
df = pd.read_excel(excel)
df.to_excel("bbb.xlsx")
