import pandas as pd

data = pd.DataFrame(
    {
        'colA': {0: 7, 1: 2, 2: 5, 3: 3, 4: 5},
        'colB': {0: 7, 1: 8, 2: 10, 3: 2, 4: 5},
        'colC': {
            0: [185, 182, 148],
            1: [117, 103, 155],
            2: [165, 184, 170],
            3: [121, 151, 187],
            4: [137, 199, 108],
        },
    }
)
print('原始数据\n', data)

print('1.使用apply解析，得到一个新的dataframe，原df不变,然后用concat和原来的拼接')
df_t = data.copy()
df1 = df_t['colC'].apply(pd.Series)
print('new df :\n', df1)
print('ori df :\n', df_t)
df_t = pd.concat([df_t, df1], axis=1)
print('con df :\n', df_t)

print('2.使用apply解析，得到一个新的dataframe，直接赋值给原df')
print('赋值的时候，字段和解析出来字段个数必须一致,字段自己命名')
df_t = data.copy()
df_t[['foo', 'bar', 'baz']] = df_t['colC'].apply(pd.Series)
print(df_t)

print(
    '以上处理是横向展开，行不变，列变多，'
    '以下是纵向展开，行变多，列不变'
)

print('使用explode展开,列名不变，直接展开了')
df = data.copy()
df1 = df.explode('colC')
print('ori df:\n', df)
print('new df:\n', df1)
