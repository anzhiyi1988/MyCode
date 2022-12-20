#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import pandas as pd

"""
行转列

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
df = pd.DataFrame({
    'A': ['A1', 'A2', 'A3'],
    'B': ['B1', 'B2', 'B3'],
    'C': ['C1', 'C2', 'C3'],
    'D': [1, 2, 3]
})

print("1. 原始数据")
print(df)

# 在用stack的时候，index是不会转的，所有把不需要转的字段设置为index
df1 = df.set_index(["A", "B"])
print("2. 设置为index后")
print(df1)

df2 = df1.stack()
print("3. 使用stack后")
print(df2)

"""
reset_index后，level_x ，这个x就是索引个数，值字段固定为0
"""
df2 = df2.reset_index()
print("4. 使用reset index")
print(df2)

df2.rename(columns={"level_2": "X1", 0: "X2"}, inplace=True)
print("5. 把名字换一下")
print(df2)
# df_new = df_new.stack().reset_index(level=1).rename(columns={0: "D"})
# print(df_new)

#     #   split_start_data = start_data["server_evaluation_ids"].str.split(
#             # ',', expand=True).stack().reset_index(level=1).rename(columns={0: "server_evaluation_id"})

# df_new1 = df["B"].str.split(',', expand=True).stack().reset_index(level=1).rename(columns={0: "D"})
# print(df_new1)

print("实验2：选择需要的字段，然后用stack -----------------------------------")
print("1. 原始数据")
print(df)
df3 = df[['C', 'D']].stack()
print("2.1 使用stack后")
print(df3)

df4 = df3.reset_index(level=1)
print("2.2 使用reset_index(level=1) ,只把第二个索引变成系列")
print(df4)

df5 = df[['A', 'B']].join(df4)
print("2.3 和原来的数据join，join函数啥也不写时，用indexjoin")
print(df5)

"""
这个是把列转行 pivot_table
"""
print("实验3：使用pivot_table 未开始 -----------------------------------")

