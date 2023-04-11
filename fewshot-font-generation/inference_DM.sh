# DM 모델 infernece 돌리는 script
# python inference.py cfgs/DM/eval.yaml cfgs/data/eval/kor_ttf.yaml \
# --model DM \
# --weight /home/elicer/Own-My-Geul/fewshot-font-generation/result/dm/checkpoints/last.pth \
# --result_dir ./result/DM_inference_2

# 고려글꼴 ttf generation
python inference.py cfgs/DM/eval.yaml cfgs/data/eval/kor_png.yaml \
--model DM \
--weight /home/elicer/Own-My-Geul/fewshot-font-generation/result/dm/checkpoints/last.pth \
--result_dir ./result/DM_inference_GoLyeo