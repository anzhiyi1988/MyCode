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

col = {
    'colA': 'colA_n',
    'colB': 'colB_n',
    'colC': 'colC'
}

df.rename(columns=col, inplace=True)
print(df)

data_po = df['colC'].apply(pd.Series)
print(data_po)

col1 = {
    'foo': 'foo_n',
    'bar': 'bar_n',
    'zoo': 'zoo_n'
}
data_po.rename(columns=col1, inplace=True)
print(data_po)

data = pd.concat([df, data_po], axis=1)
print(data)
