import math

def VernamCypher( text, key ):
    answer = "" # the Cypher text
    p = 0 # pointer for the key
    for char in text:
        answer += chr(ord(char) ^ ord(key[p]))
        p += 1
        if p==len(key):
            p = 0
    return answer


def Row(msg,key): 
    cipher = "" 
  
    # track key indices 
    index = 0
  
    msg_len = float(len(msg)) 
    msg_list = list(msg) 
    key_list = sorted(list(key)) 
  
    #matrix is of dimension row x col
    
    col = len(key) 
    row = int(math.ceil(msg_len / col)) 
  
    # add the padding character '_' for empty cells
    padding = int((row * col) - msg_len) 
    msg_list.extend('_' * padding) 
  
    #print(msg_list)

    #create the matrix 
    matrix = [msg_list[i: i + col]  
              for i in range(0, len(msg_list), col)] 
  
    # print("Matrix Construction...\n")
    #print(matrix)

    # read matrix column-wise using key 
    for _ in range(col): 
        curr_idx = key.index(key_list[index]) 
        cipher += ''.join([row[curr_idx]  
                          for row in matrix]) 
        #print(cipher)
        index += 1
  
    return cipher 
  
# Decryption 
def decryptRow(cipher,key, flag = 0): 
    msg = "" 
  
    # track key indices 
    index = 0
  
    # track msg indices 
    msg_indx = 0
    msg_len = float(len(cipher)) 
    msg_list = list(cipher) 
  
    # calculate column of the matrix 
    col = len(key) 
    row = int(math.ceil(msg_len / col)) 
  
    # convert key into list and sort  
    # alphabetically so we can access  
    # each character by its alphabetical position. 
    key_list = sorted(list(key)) 
  
    # create an empty matrix to  
    # store deciphered message 
    deciphered = [] 

    for _ in range(row): 
        deciphered += [[None] * col] 
  
    # Arrange the matrix column wise according  
    # to permutation order by adding into new matrix 
    for _ in range(col): 
        curr_idx = key.index(key_list[index]) 
  
        for j in range(row): 
            deciphered[j][curr_idx] = msg_list[msg_indx] 
            msg_indx += 1
        index += 1
    #print(deciphered)
    # convert decrypted msg matrix into a string 
    try: 
        msg = ''.join(sum(deciphered, [])) 
    except TypeError: 
        raise TypeError("This program cannot handle repeating words.") 
    if flag == 1:
        return msg
    
    else:
      null_count = msg.count('_') 
  
      if null_count > 0: 
          return msg[: -null_count] 
  
      return msg

    
MY_KEY = "pluto"

PlainText = input("Enter text to encrypt: ")

print("\n\n---Vernam Cypher---")
Cypher = VernamCypher(PlainText, MY_KEY)
print("Cypher Text: "+Cypher)
   
print("\n\n---Row Cypher---")
Row1 = Row(Cypher,MY_KEY)
print("Cypher Text: "+Row1)

print("\n\n---Double Row Cypher---")
Row2 = Row(Row1,MY_KEY)
print("Cypher Text: "+Row2)

print("\n\n---Double Decipher---")
decrypt2 = decryptRow(Row2,MY_KEY)
print("Decrypt msg: "+decrypt2)

print("\n\n---Row Decipher---")
decrypt1 = decryptRow(decrypt2,MY_KEY)
print("Decrypt msg: "+decrypt1)

print("\n\n---Vernam Decipher---")
decrypt = VernamCypher(decrypt1, MY_KEY)
print("Decrypt msg: "+decrypt)
