import cv2

chars = ['거','값','같','곬','곶','깎','넋','늪','닫','닭','닻','됩','뗌','략','몃','밟','볘','뺐','뽈','솨','쐐','앉','않','얘','얾','엌','옳','읊','죡','쮜','춰','츄','퀭','틔','핀','핥','훟',
'겸','꼡','놊','돳','뙜','룅','묮','붗','쀀','쉩','쓒','윻','즉','쯲','칛','칸','탡','퍊','햳','걸','껡','녊','뎳','똜','뢅','뫮','뵗',
'뿀','숩','쒒','웻','쥤','쯍','츶','킟','팈','팥','햎','걓','꺡','넊','덳','뗜','롅','몮','봗','뾀','쇩','쑒','욻','줤','쮍','췶','큟',
'틈','픱','힚','갓','깼','냥','덎','떷','렠','몉','볲','뽛','솩','쐒','왻','죤','쭍','춶','퀟','튈','퓱','흚','긟','낈','낥','댎','땷']
file_path = '안녕하세요.jpg'
file_path2 = '조용재2.jpg'
image1 = cv2.imread(file_path)
image2 = cv2.imread(file_path2)
width = 205
height = 350
x = 230
y = 115
save_x = x
save_y = y
cnt=1
for j in range(1):
    if j == 5:
        x = save_x
    if j < 5:
        for i in range(12):
            crop = image1[x:x+width, y:y+width]
            cv2.imwrite(f"../data/{chars[cnt-1]}.png", crop)
            y = y + width + 3
            cnt+=1
    else:
        for i in range(12):
            if cnt == 119:
                break
            crop = image2[x:x+width, y:y+width]
            cv2.imwrite(f"../data/{chars[cnt-1]}.png", crop)
            y = y + width + 3
            cnt+=1
    y = save_y
    x = x + height
    