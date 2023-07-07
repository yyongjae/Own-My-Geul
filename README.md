# Own-My-Geul

# Introduction

- 프로젝트 개요 및 목적
    - 사용자의 손글씨를 폰트로 만듦
- 기술 스택 및 사용된 AI 모델 소개
    - Clova 의 https://github.com/clovaai/fewshot-font-generation 를 이용
    - 제공해주는 4개의 모델 중 DM-Font 모델 사용
- 프로젝트의 주요 기능 설명
    - 제공된 템플릿을 따라 사용자 손글씨 118글자 작성
    - Train : 사용자 손글씨 118글자 학습
    - Inference : 학습한 손글씨 기반 약 2300개의 한글 문자 생성
# Flowchart
<img src="/Users/yongcho/dev/yonggit/Own-My-Geul/img/flowchart.png">

# Data

### Training Dataset

```bash
학습에 사용한 글자 118개
거값같곬곶깎넋늪닫닭닻됩뗌략몃밟볘뺐뽈솨쐐앉않얘얾엌옳읊죡쮜춰츄퀭
틔핀핥훟겸꼡놊돳뙜룅묮붗쀀쉩쓒윻쯲칛칸탡퍊햳걸껡녊뎳똜뢅뫮뵗뿀숩쒒
웻쥤쯍츶킟팈팥햎걓꺡넊덳뗜롅몮봗뾀쇩쑒욻줤쮍췶큟틈픱힚갓깼냥덎떷렠
몉볲뽛솩쐒왻죤쭍춶퀟튈퓱흚긟낈낥댎땷
```

**1️⃣ 나눔손글씨 고려글꼴**

https://clova.ai/handwriting/list.html

위의 링크에서 TTF 파일을 다운받아 정해놓은 118개의 글자를 추출하여 학습 데이터로 사용할 수 있다.

**2️⃣ 사용자의 손글씨**

1. 만들어 놓은 템플릿에 직접 118개의 글자를 직접 작성한다.


<img src="/Users/yongcho/dev/yonggit/Own-My-Geul/img/template.png">


1. 해당 파일을 `Own-My-Geul/utils` 폴더에 넣는다.
2. `Own-My-Geul/utils` 에 있는 `fileconvert.py` 를 실행 시키면 `Own-My-Geul/data` 폴더에 학습을 위한 PNG 데이터가 생성된다.

# Model

- DM, FUNIT, LF, MX 중 DM 모델을 사용
- DM-Font 모델 구조
    
    <img src="/Users/yongcho/dev/yonggit/Own-My-Geul/img/dm-architecture.png">
    
- DM-Font 논문 리뷰 → [[논문] DM-Font 논문 읽고 정리하기](https://www.notion.so/DM-Font-4a53442585104a77ae5b21ae4e3d8692?pvs=21)

## Training

- 학습 파라미터 설정 및 하이퍼파라미터 튜닝 방법
- ~~학습 데이터 전처리 및 데이터 로더 구현~~
- 학습 과정 및 결과 시각화
1. train 쉘 스크립트 실행 : `sh train_DM.sh`
    
    사용자 환경에 맞게 `cfgs/DM/train.yaml`, `cfgs/data/train/kor_dmfont_png.yaml` 파일 수정
    
2. 학습한 가중치 파일을 이용해 eval 쉘 스크립트 실행  : `sh inference_DM.sh`
    
    사용자 환경에 맞게 `cfgs/DM/eval.yaml`, `cfgs/data/eval/kor_png.yaml` 파일 수정
    
    - model : 4개의 모델 중 DM-Font 모델을 사용
    - weight : 가중치 파일의 경로
    - result_dir : 결과 저장 디렉토리 (약 2300개의 한글 글자 생성)

## Results

### 서비스 페이지
<img src="/Users/yongcho/dev/yonggit/Own-My-Geul/img/page.png">

### 생성 결과
