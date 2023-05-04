import os
import pandas as pd
import re

file_path = './UMAFall_Dataset/UMAFall_Subject_01_ADL_Aplausing_1_2017-04-14_23-38-23.csv'
df = pd.read_csv('./UMAFall_Dataset/UMAFall_Subject_01_ADL_Aplausing_1_2017-04-14_23-38-23.csv', skiprows=40, sep=';')

#get file type
if (re.match(r'.*ADL.*', file_path)):

    df.insert(loc=1,
          column='FileType',
          value="ADL")
elif (re.match(r'.*FALL.*', file_path)):
    df["FileType"] = 'FALL'

# Salvar o DataFrame atualizado em um novo arquivo CSV
df.to_csv('newCSV.csv', index=False)



path_of_the_directory= './UMAFall_Dataset'

df = pd.DataFrame(columns=['TimeStamp', 'Sample No', 'X-Axis', 'Y-Axis', 'Z-Axis', 'Sensor Type', 'Sensor ID'])

for filename in os.listdir(path_of_the_directory):
    f = os.path.join(path_of_the_directory,filename)
    if os.path.isfile(f):
        df_actual = pd.read_csv('./UMAFall_Dataset/UMAFall_Subject_01_ADL_Aplausing_1_2017-04-14_23-38-23.csv', skiprows=41, sep=';')
        filename = f.split('/')[-1]
        print(filename)

        #get file type
        if (re.match(r'.*ADL.*', file_path)):
            df_actual.insert(loc=1,
                  column='FileType',
                  value="ADL")
        elif (re.match(r'.*FALL.*', file_path)):
            df_actual.insert(loc=1,
                  column='FileType',
                  value="FALL")
            
        
        df = df.append(df_actual, ignore_index=True)
        

df.to_csv('newCSV.csv', index=False)