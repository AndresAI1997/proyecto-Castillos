import json
from pathlib import Path
path = Path('EDA_Castle.ipynb')
data = json.loads(path.read_text(encoding='utf-8'))
code_cells = [cell for cell in data['cells'] if cell.get('cell_type')=='code']
first = code_cells[0]
source = first['source']
if all('pathlib' not in line for line in source):
    source.insert(3, 'from pathlib import Path\n')
df_cell = code_cells[1]
new_block = [
    '# Cargar archivo TSV de castillos\n',
    'possible_paths = [\n',
    '    Path.cwd() / "castillos_europa_10000.tsv",\n',
    '    Path.cwd().parent / "castillos_europa_10000.tsv"\n',
    ']\n',
    'for candidate in possible_paths:\n',
    '    if candidate.exists():\n',
    '        data_path = candidate\n',
    '        break\n',
    'else:\n',
    '    raise FileNotFoundError("No se encontró castillos_europa_10000.tsv en rutas esperadas")\n',
    'df = pd.read_csv(data_path, sep="\\t")\n',
    'print("Dimensiones del dataset:", df.shape)\n',
    'df.head()\n'
]
df_cell['source'] = new_block
path.write_text(json.dumps(data, indent=1, ensure_ascii=False), encoding='utf-8')
