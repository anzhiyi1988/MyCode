#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
原始数据
    A   B   C   D
0  A1  B1  C1  D1
1  A2  B2  C2  D2
2  A3  B3  C3  D3

目标数据

    A   B  X1 X2
0  A1  B1  C  C1
   A1  B1  D  D1
1  A2  B2  C  C2
   A2  B2  D  D2
2  A3  B3  C  C3
   A3  B3  D  D3


"""

import pandas as pd

df = pd.DataFrame({'A': ['A1', 'A2', 'A3'], 'B': ['B1', 'B2', 'B3'], 'C': ['C1', 'C2', 'C3'], 'D': ['D1', 'D2', 'D3']})
print(df)
print("-----------------------")

df1 = df.set_index(["A", "B"])
print(df1)

print("-----------------------")
df2 = df1.stack()
print(df2)

print("-----------------------reset index")
df2 = df2.reset_index()
print(df2)

print("-----------------------")

df2.rename(columns={"level_2": "X1", 0: "X2"}, inplace=True)
print(df2)
print("-----------------------")
# df_new = df_new.stack().reset_index(level=1).rename(columns={0: "D"})
# print(df_new)

#     #   split_start_data = start_data["server_evaluation_ids"].str.split(
#             # ',', expand=True).stack().reset_index(level=1).rename(columns={0: "server_evaluation_id"})

# df_new1 = df["B"].str.split(',', expand=True).stack().reset_index(level=1).rename(columns={0: "D"})
# print(df_new1)
