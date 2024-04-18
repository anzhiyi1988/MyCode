import pandas as pd

df = pd.DataFrame(
    {
        'colA': {0: 7, 1: 2, 2: 5, 3: 3, 4: 5},
        'colB': {0: 7, 1: 8, 2: 10, 3: 2, 4: 5},
        'colC': {
            0: {'foo': 185, 'bar': 182, 'baz': 148},
            1: {'foo': 117, 'bar': 103, 'baz': 155},
            2: {'foo': 165, 'bar': 184, 'baz': 170},
            3: {'foo': 121, 'bar': 151, 'baz': 187},
            4: {'foo': 137, 'bar': 199, 'baz': 108},
        },
    }
)
print('原始数据\n', df)

print('1.使用pd的json_normal解析，得到一个新的dataframe，原df不变,然后用concat和原来的拼接')
df_t = df.copy()
df1 = pd.json_normalize(df_t.colC)
print(df1)
print(df_t)
df_t = pd.concat([df_t, df1], axis=1)
print(df_t)

print('2.使用pd的json_normal解析，得到一个新的dataframe，直接赋值给原df')
print('赋值的时候，字段和解析出来字段个数必须一致,字段名可以不一样，如果想用原来的名字，顺序必须一致')
df_t = df.copy()
df_t[['foo', 'bar', 'baz']] = pd.json_normalize(df_t.colC)
print(df_t)

print('3.使用apply解析，得到一个新的dataframe，原df不变,然后用concat和原来的拼接')
df_t = df.copy()
df1 = df_t['colC'].apply(pd.Series)
print(df1)
print(df_t)
df_t = pd.concat([df_t, df1], axis=1)
print(df_t)

print('4.使用apply解析，得到一个新的dataframe，直接赋值给原df')
print('赋值的时候，字段和解析出来字段个数必须一致,字段名可以不一样，如果想用原来的名字，顺序必须一致')
df_t = df.copy()
df_t[['foo', 'bar', 'baz']] = df_t['colC'].apply(pd.Series)
print(df_t)

print(
    'map中的字段如果很多，可以采用1 3 的concat方法，这里的解析，是行数不变的，列数变多，'
    '有没有一种方式吧map解析成两列，行数变多？'
)