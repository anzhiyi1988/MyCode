import xml.etree.ElementTree as ET
import pandas as pd

import code_conf as cd

tree = ET.parse('general.xml')
root = tree.getroot()
g_list = []
for child in root:
    g_attr = child.attrib
    _id = g_attr['id']
    _city_id = g_attr.get('cityid')
    _camp_id = g_attr.get('campid')
    _exp = g_attr.get('exp')
    _ability = child.find('ability')
    _dynamicability = child.find('dynamicability')
    _general = {}
    if _ability is not None:
        _ability = _ability.text.split(',')
        _dynamicability = _dynamicability.text.split(',')
        _general = {a: int(b) + int(c) for a, b, c in zip(cd.general_ability_code2, _ability, _dynamicability)}
    _general['gid'] = _id
    _general['city_id'] = _city_id
    _general['camp_id'] = _camp_id
    _general['exp'] = _exp
    g_list.append(_general)

df = pd.DataFrame(g_list)
df['gname'] = df['gid'].map(cd.general_code)
df['city_name'] = df['city_id'].map(cd.city_code)
df['role'] = df['gname'].map(cd.general_role)
df[df['camp_id'] == '3'].to_excel('general.xlsx')
