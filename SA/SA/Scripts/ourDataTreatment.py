import json
import csv


file = open("backup.json", "r")

jsonData = json.loads(file.read())

collections = jsonData['__collections__']

testes = collections['testes']


with open('ourDS.csv', 'w', newline='') as file:
    writer = csv.writer(file)
    writer.writerow(["TestId", "SampleNum", "X-Axis", "Y-Axis", "Z-Axis","SensorType","Fall"])

    for teste in testes.values():
        queda = '0'

        if teste['label'] == 'Queda':
            queda = '1'
        
        for data in teste['accelerometerData']:
            writer.writerow([teste['id'], data['id'], data['valueX'],data['valueY'],data['valueZ'],"0",queda ])
        for data in teste['gyroscopicData']:
            writer.writerow([teste['id'], data['id'], data['valueX'],data['valueY'],data['valueZ'],"1",queda ])
        for data in teste['magnetometerData']:
            writer.writerow([teste['id'], data['id'], data['valueX'],data['valueY'],data['valueZ'],"2",queda ])



