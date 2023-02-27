# Contributing

## Guideline

1. `main` 브랜치는 최종본만 업로드합니다.
2. `dev` 브랜치에 실질적으로 필요한 모든 코드를 업로드합니다. 기능 개발, 실험은 feat-{기능명}(ex. feat-db_connect)과 같은 브랜치를 만들어서 push하고 dev에 merge합니다.
3. vscode 코드 에디터가 제공하는 기능을 사용하여 코드 정리하기. __cmd + k + f__
4. git commit convention
    - fix : 버그 패치
    - feat : 기능 추가
    - style : 기능 변경 없이 간단한 변수명, 파일명, 경로변경 등의 작업
    - design : 프로그램 UI 변경
    - BREAKING CHANGE : 커다란 API의 변경
    - HOTFIX : 급하게 큰 에러의 수정한 경우
    - refactor : 기능 변경 없이 레거시를 리팩터링하는 거대한 작업
    - docs : 기능 변경 없이 문서 및 주석 수정 (코드 수정 X)
    - remove : 삭제
    - first commit

5. __commit message__
```text
<convention>: 한 줄 설명(이슈링크) -> 명령어 사용, 점(.)X
# 한 줄 띄우기
- <커밋에 대한 본문 설명>

```

`example`
```text
fix: loading data error fix (#102)

- load.py의 DB에서 데이터를 받아오는 부분의 에러를 수정
- main.py에서 load.py의 함수를 호출하는 부분 수정
```

6. __PR__ 의 제목은 commit message와 동일하게 합니다.

7. __ISSUE__ 의 제목은 [CONVENTION]: 내용 의 구성으로 합니다
    - ex. [FEAT]: 서버 연결 기능 구현

    





### 참고

- [CONTRIBUTING.md template](https://gist.github.com/PurpleBooth/b24679402957c63ec426)
- [VSCode Linting](https://code.visualstudio.com/docs/python/linting)
- [Python PEP8](https://peps.python.org/pep-0008/)
- [Git commit convention](https://www.conventionalcommits.org/ko/v1.0.0/)
- [Git commit convention reference: FastAPI Template project](https://github.com/tiangolo/full-stack-fastapi-postgresql)
- [Sementic versioning](https://semver.org/lang/ko/)
- [Repository convention reference: SSAFY BookFlex project](https://github.com/glenn93516/BookFlex)