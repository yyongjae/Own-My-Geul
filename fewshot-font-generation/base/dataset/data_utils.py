"""
FFG-benchmarks
Copyright (c) 2021-present NAVER Corp.
MIT license
"""
import random
import json
from pathlib import Path
from tqdm import tqdm
from collections import defaultdict

from .ttf_utils import read_font


def sample(population, k):
    if len(population) < k:
        sampler = random.choices
    else:
        sampler = random.sample
    sampled = sampler(population, k=k) # ✅
    return sampled


def load_img_data(data_dirs, char_filter=None, extension="png", n_font=None):
    # ✅ data_dirs : ['data_example/kor/png']
    
    data_dirs = [data_dirs] if not isinstance(data_dirs, list) else data_dirs
    # ✅ data_dirs : ['data_example/kor/png']
    char_filter = set(char_filter) if char_filter is not None else None
    # char_filter : {'곶', '늪', '볘', '밟', '핥', '틔', '닻', '엌', '같', '훟', '깎', '닭', '얘', '쐐', '뗌', '퀭', '됩', '값', '곬', '넋', '솩', '략', '얾', '쮜', '앉', '닫', '뺏', '읊', '않', '핀', '죡', '뽈', '몃', '옳', '춰', '츄'}

    key_dir_dict = defaultdict(dict)
    key_char_dict = defaultdict(list)

    for pathidx, path in enumerate(data_dirs):
        # ✅ path : data_example/kor/png
        _key_dir_dict, _key_char_dict = load_img_data_from_single_dir(path, char_filter, extension, n_font)
        '''
        ✅ _key_dir_dict : {    'UhBee_HanByeol': {'깎': PosixPath('data_example/kor/png/UhBee_HanByeol'), '옳': PosixPath('data_example/kor/png/UhBee_HanByeol'), '뗌': PosixPath('data_example/kor/png/UhBee_HanByeol'), '솩': PosixPath('data_example/kor/png/UhBee_HanByeol'), '같': PosixPath('data_example/kor/png/UhBee_HanByeol'), '략': PosixPath('data_example/kor/png/UhBee_HanByeol'), '늪': PosixPath('data_example/kor/png/UhBee_HanByeol'), '앉': PosixPath('data_example/kor/png/UhBee_HanByeol'), '얘': PosixPath('data_example/kor/png/UhBee_HanByeol'), '쮜': PosixPath('data_example/kor/png/UhBee_HanByeol'), '넋': PosixPath('data_example/kor/png/UhBee_HanByeol'), '퀭': PosixPath('data_example/kor/png/UhBee_HanByeol'), '볘': PosixPath('data_example/kor/png/UhBee_HanByeol'), '훟': PosixPath('data_example/kor/png/UhBee_HanByeol'), '값': PosixPath('data_example/kor/png/UhBee_HanByeol'), '얾': PosixPath('data_example/kor/png/UhBee_HanByeol'), '핀': PosixPath('data_example/kor/png/UhBee_HanByeol'), '죡': PosixPath('data_example/kor/png/UhBee_HanByeol'), '닭': PosixPath('data_example/kor/png/UhBee_HanByeol'), '츄': PosixPath('data_example/kor/png/UhBee_HanByeol'), '핥': PosixPath('data_example/kor/png/UhBee_HanByeol'), '밟': PosixPath('data_example/kor/png/UhBee_HanByeol'), '닻': PosixPath('data_example/kor/png/UhBee_HanByeol'), '춰': PosixPath('data_example/kor/png/UhBee_HanByeol'), '곶': PosixPath('data_example/kor/png/UhBee_HanByeol'), '닫': PosixPath('data_example/kor/png/UhBee_HanByeol'), '됩': PosixPath('data_example/kor/png/UhBee_HanByeol'), '않': PosixPath('data_example/kor/png/UhBee_HanByeol'), '쐐': PosixPath('data_example/kor/png/UhBee_HanByeol'), '틔': PosixPath('data_example/kor/png/UhBee_HanByeol'), '읊': PosixPath('data_example/kor/png/UhBee_HanByeol'), '곬': PosixPath('data_example/kor/png/UhBee_HanByeol'), '뽈': PosixPath('data_example/kor/png/UhBee_HanByeol'), '엌': PosixPath('data_example/kor/png/UhBee_HanByeol'), '몃': PosixPath('data_example/kor/png/UhBee_HanByeol')}, 
                                'UhBee_charming': {'깎': PosixPath('data_example/kor/png/UhBee_charming'), '옳': PosixPath('data_example/kor/png/UhBee_charming'), '뗌': PosixPath('data_example/kor/png/UhBee_charming'), '솩': PosixPath('data_example/kor/png/UhBee_charming'), '같': PosixPath('data_example/kor/png/UhBee_charming'), '략': PosixPath('data_example/kor/png/UhBee_charming'), '늪': PosixPath('data_example/kor/png/UhBee_charming'), '앉': PosixPath('data_example/kor/png/UhBee_charming'), '얘': PosixPath('data_example/kor/png/UhBee_charming'), '쮜': PosixPath('data_example/kor/png/UhBee_charming'), '넋': PosixPath('data_example/kor/png/UhBee_charming'), '퀭': PosixPath('data_example/kor/png/UhBee_charming'), '볘': PosixPath('data_example/kor/png/UhBee_charming'), '훟': PosixPath('data_example/kor/png/UhBee_charming'), '값': PosixPath('data_example/kor/png/UhBee_charming'), '얾': PosixPath('data_example/kor/png/UhBee_charming'), '핀': PosixPath('data_example/kor/png/UhBee_charming'), '죡': PosixPath('data_example/kor/png/UhBee_charming'), '닭': PosixPath('data_example/kor/png/UhBee_charming'), '츄': PosixPath('data_example/kor/png/UhBee_charming'), '핥': PosixPath('data_example/kor/png/UhBee_charming'), '밟': PosixPath('data_example/kor/png/UhBee_charming'), '닻': PosixPath('data_example/kor/png/UhBee_charming'), '춰': PosixPath('data_example/kor/png/UhBee_charming'), '곶': PosixPath('data_example/kor/png/UhBee_charming'), '닫': PosixPath('data_example/kor/png/UhBee_charming'), '됩': PosixPath('data_example/kor/png/UhBee_charming'), '않': PosixPath('data_example/kor/png/UhBee_charming'), '쐐': PosixPath('data_example/kor/png/UhBee_charming'), '틔': PosixPath('data_example/kor/png/UhBee_charming'), '읊': PosixPath('data_example/kor/png/UhBee_charming'), '곬': PosixPath('data_example/kor/png/UhBee_charming'), '뽈': PosixPath('data_example/kor/png/UhBee_charming'), '엌': PosixPath('data_example/kor/png/UhBee_charming'), '몃': PosixPath('data_example/kor/png/UhBee_charming')}}
        ✅ _key_char_dict : {   'UhBee_HanByeol': ['깎', '옳', '뗌', '솩', '같', '략', '늪', '앉', '얘', '쮜', '넋', '퀭', '볘', '훟', '값', '얾', '핀', '죡', '닭', '츄', '핥', '밟', '닻', '춰', '곶', '닫', '됩', '않', '쐐', '틔', '읊', '곬', '뽈', '엌', '몃'], 
                                'UhBee_charming': ['깎', '옳', '뗌', '솩', '같', '략', '늪', '앉', '얘', '쮜', '넋', '퀭', '볘', '훟', '값', '얾', '핀', '죡', '닭', '츄', '핥', '밟', '닻', '춰', '곶', '닫', '됩', '않', '쐐', '틔', '읊', '곬', '뽈', '엌', '몃']}
        '''
        for _key in _key_char_dict:
            key_dir_dict[_key].update(_key_dir_dict[_key])
            key_char_dict[_key] += _key_char_dict[_key]
            key_char_dict[_key] = sorted(set(key_char_dict[_key]))

    return dict(key_dir_dict), dict(key_char_dict)


def load_img_data_from_single_dir(data_dir, char_filter=None, extension="png", n_font=None):
    data_dir = Path(data_dir)

    key_dir_dict = defaultdict(dict)
    key_char_dict = {}

    fonts = [x.name for x in data_dir.iterdir() if x.is_dir()]
    if n_font is not None:
        fonts = sample(fonts, n_font)
    # ✅ fonts : ['UhBee_HanByeol', 'UhBee_charming', 'NaNum_GoLyeo']
    # ✅ n_font : 3
    for key in fonts:
        # print(key)
        # for x in (data_dir / key).glob(f"*.png"):
        #     print(x)
        
        chars = [x.stem for x in (data_dir / key).glob(f"*.{extension}")]
        '''
        UhBee_HanByeol's chars : ['값', '같', '곬', '곶', '깎', '넋', '늪', '닫', '닭', '닻', '됩', '뗌', '략', '몃', '밟', '볘', '뺐', '뽈', '솩', '쐐', '앉', '않', '얘', '얾', '엌', '옳', '읊', '죡', '쮜', '춰', '츄', '퀭', '틔', '핀', '핥', '훟']
        UhBee_charming's chars : ['값', '같', '곬', '곶', '깎', '넋', '늪', '닫', '닭', '닻', '됩', '뗌', '략', '몃', '밟', '볘', '뺐', '뽈', '솩', '쐐', '앉', '않', '얘', '얾', '엌', '옳', '읊', '죡', '쮜', '춰', '츄', '퀭', '틔', '핀', '핥', '훟']
        NaNum_GoLyeo's chars :   ['값', '같', '곬', '곶', '깎', '넋', '늪', '닫', '닭', '닻', '됩', '뗌', '략', '몃', '밟', '볘', '뺐', '뽈', '솩', '쐐', '앉', '않', '얘', '얾', '엌', '옳', '읊', '쮜', '죡', '춰', '츄', '퀭', '핀', '틔', '핥', '훟']
        char_filter : {'엌', '않', '뽈', '늪', '틔', '략', '훟', '밟', '얘', '값', '넋', '닫', '곶', '됩', '춰', '핀', '솩', '볘', '쐐', '같', '얾', '뺏', '옳', '뗌', '닻', '핥', '쮜', '앉', '죡', '츄', '깎', '퀭', '읊', '곬', '닭', '몃'}
        char_filter : {'엌', '않', '뽈', '늪', '틔', '략', '훟', '밟', '얘', '값', '넋', '닫', '곶', '됩', '춰', '핀', '솩', '볘', '쐐', '같', '얾', '뺏', '옳', '뗌', '닻', '핥', '쮜', '앉', '죡', '츄', '깎', '퀭', '읊', '곬', '닭', '몃'}
        char_filter : {'엌', '않', '뽈', '늪', '틔', '략', '훟', '밟', '얘', '값', '넋', '닫', '곶', '됩', '춰', '핀', '솩', '볘', '쐐', '같', '얾', '뺏', '옳', '뗌', '닻', '핥', '쮜', '앉', '죡', '츄', '깎', '퀭', '읊', '곬', '닭', '몃'}
        '''
        # print(key)
        # print(chars)
        # print(char_filter)
        # print(set(chars).intersection(char_filter))
        if char_filter is not None:
            chars = list(set(chars).intersection(char_filter))
        # print(key)
        # print(chars)
        '''
        UhBee_HanByeol's chars : ['죡', '곶', '츄', '엌', '됩', '읊', '틔', '춰', '값', '앉', '깎', '얘', '밟', '훟', '않', '쐐', '같', '쮜', '솩', '략', '퀭', '늪', '닭', '닻', '몃', '뽈', '볘', '핥', '핀', '곬', '닫', '옳', '뗌', '얾', '넋']
        UhBee_charming's chars : ['죡', '곶', '츄', '엌', '됩', '읊', '틔', '춰', '값', '앉', '깎', '얘', '밟', '훟', '않', '쐐', '같', '쮜', '솩', '략', '퀭', '늪', '닭', '닻', '몃', '뽈', '볘', '핥', '핀', '곬', '닫', '옳', '뗌', '얾', '넋']
        NaNum_GoLyeo's chars : ['죡', '곶', '츄', '엌', '됩', '읊', '틔', '춰', '값', '앉', '깎', '얘', '밟', '훟', '않', '쐐', '같', '쮜', '솩', '략', '퀭', '늪', '닭', '닻', '몃', '뽈', '볘', '핥', '핀', '곬', '닫', '옳', '뗌', '얾', '넋']
        '''

        if not chars:
            print(key, "is excluded! (no available characters)")
            continue
        else:
            key_char_dict[key] = list(chars)
            for char in chars:
                key_dir_dict[key][char] = (data_dir / key)

    return dict(key_dir_dict), key_char_dict


def load_ttf_data(data_dirs, char_filter=None, extension="ttf", n_font=None):
    data_dirs = [data_dirs] if not isinstance(data_dirs, list) else data_dirs
    char_filter = set(char_filter) if char_filter is not None else None

    key_font_dict = {}
    key_char_dict = {}

    for pathidx, path in enumerate(data_dirs):
        _key_font_dict, _key_char_dict = load_ttf_data_from_single_dir(path, char_filter, extension, n_font)
        key_font_dict.update(_key_font_dict)
        key_char_dict.update(_key_char_dict)

    return key_font_dict, key_char_dict


def load_ttf_data_from_single_dir(data_dir, char_filter=None, extension="ttf", n_font=None):
    data_dir = Path(data_dir)
    font_paths = sorted(data_dir.glob(f"*.{extension}"))
    if n_font is not None:
        font_paths = sample(font_paths, n_font)

    key_font_dict = {}
    key_char_dict = {}

    for font_path in font_paths:
        key = font_path.stem

        with open(str(font_path).replace(f".{extension}", ".txt"), encoding="utf-8") as f:
            chars = f.read()
        if char_filter is not None:
            chars = set(chars).intersection(char_filter)

        if not chars:
            print(font_path.name, "is excluded! (no available characters)")
            continue
        else:
            font = read_font(font_path)
            key_font_dict[key] = font
            key_char_dict[key] = list(chars)

    return key_font_dict, key_char_dict
