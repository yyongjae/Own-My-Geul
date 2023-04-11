import React, {useState} from 'react';
import FontTemplate from '../components/FontTemplate';
import Button from '../components/Button';
import uploadIcon from '../assets/upload.svg';
import { Link } from 'react-router-dom';
import UploadButton from '../components/UploadButton';
import FontGenerateButton from '../components/FontGenerateButton';
import styles from '../styles/Main.module.css'
import axios from 'axios';

const Main = () => {
    const [uploadedFile, setUploadedFile] = useState(null);

    const handleFileUpload = (file) => {
        // 파일 업로드 처리 로직
        setUploadedFile(file);
    };
    const arr = ["갊", "갸", "곁", "곬", "교", "높", "뉑", "닳", "무", "벚", "숱", "펾"];

    const sendImageToServer = async () => {
        const formData = new FormData();
        formData.append('handwriting', uploadedFile);

        try {
            const response = await axios.post('http://localhost:8080/api/v1/font/new', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            });

            console.log(response.data); // 외부 URL로 전송된 파일의 정보 출력
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <div className={styles["main"]}>
            <div className={styles["title"]}>
                <span>손글씨 업로드하기</span>
                <p>아래 12글자를 공책이나 테블릿에서 써서 업로드 해주세요.</p>
            </div>

            <div className={styles["template"]}>
                <FontTemplate list={arr} />
            </div>

            <div>
                <UploadButton onFileUpload={handleFileUpload} content="손글씨 업로드" icon={uploadIcon} />
            </div>

            <div className={styles["navigation"]}>
                <Link style={{ marginRight: "auto" }} to={"/"}>
                    <Button type="prev" content="이전으로" />
                </Link>
                <FontGenerateButton uploadedFile={uploadedFile} content="폰트 만들기" onFileUpload={sendImageToServer} />
            </div>
        </div>
    );
};

export default Main;