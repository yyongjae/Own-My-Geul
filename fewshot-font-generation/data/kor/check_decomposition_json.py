import json # import json module
# read json
with open('/home/elicer/fewshot-font-generation/data/kor/decomposition_DM.json') as json_file:
    json_data = json.load(json_file)

# print json
print(json_data)