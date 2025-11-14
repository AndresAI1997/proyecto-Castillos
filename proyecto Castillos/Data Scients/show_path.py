import json
from pathlib import Path
path = Path('EDA_Castle.ipynb')
data = json.loads(path.read_text(encoding='utf-8'))
for idx, line in enumerate(data['cells'][0]['source']):
    if 'castillos_europa_10000' in line:
        print(idx, line.strip())
