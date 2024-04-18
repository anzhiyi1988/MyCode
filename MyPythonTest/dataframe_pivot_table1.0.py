import pandas as pd

data = [
    {'col1': 1, 'col2': 1, 'col3': 'c1', 'col4': 't1', 'col5': 't2'},
    {'col1': 1, 'col2': 1, 'col3': 'c2', 'col4': 't1', 'col5': 't2'},
    {'col1': 1, 'col2': 1, 'col3': 'c3', 'col4': 't1', 'col5': 't2'},
    {'col1': 1, 'col2': 2, 'col3': 'c1', 'col4': 't1', 'col5': 't2'},
    {'col1': 1, 'col2': 2, 'col3': 'c2', 'col4': 't1', 'col5': 't2'},
    {'col1': 1, 'col2': 2, 'col3': 'c3', 'col4': 't1', 'col5': 't2'},
    {'col1': 1, 'col2': 2, 'col3': 'c4', 'col4': 't1', 'col5': 't2'}
]
df1 = pd.DataFrame(data)
print(df1)

df2 = df1.pivot_table(
    index=['col1', 'col2'],
    columns=['col3'],
    values=['col4', 'col5'],
    aggfunc={'col4': max, 'col5': max}
)

print(df2)
print(df2.columns)


print(df2.reset_index())
print(df2.reset_index().columns)


cols = list(sub_step.columns)
new_cols = [(i[1] + '_' + i[0]) for i in cols]
sub_step.columns = new_cols
sub_step.rename(columns={'_demand_order_id': 'demand_order_id', '_server_id': 'server_id'}, inplace=True)