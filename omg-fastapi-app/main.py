from fastapi import FastAPI, Form, File, UploadFile
from fastapi.responses import FileResponse

app = FastAPI()

@app.get("/")
def read_root():
    return {"Hello": "World"}

@app.post("/upload")
async def upload():
    # print(handwriting)
    return FileResponse("GamjaFlower-Regular.ttf", filename="new-font.ttf")