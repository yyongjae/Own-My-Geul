import React, {useState} from 'react';
import Button from '../components/Button';
import uploadIcon from '../assets/upload.svg';
import { Link, useNavigate } from 'react-router-dom';
import UploadButton from '../components/UploadButton';
import FontGenerateButton from '../components/FontGenerateButton';
import styles from '../styles/Main.module.css'
import api from '../axiosInstance';
import DownloadButton from '../components/buttons/DownloadButton';
import template from '../assets/omgTemplate.pdf'

const Main = () => {
    const [uploadedFile, setUploadedFile] = useState(null);

    const handleFileUpload = (file) => {
        // 파일 업로드 처리 로직
        setUploadedFile(file);
    };

    const navigate = useNavigate();

    const sendImageToServer = async () => {
        if (uploadedFile === null) {
            alert("먼저 파일을 업로드해주세요!")
        }
        else{
            const formData = new FormData();
            formData.append('handwriting', uploadedFile);
            api.post('/api/v1/font/new', formData, {
                headers: {
                    'Content-Type': 'multipart/form-data',
                }
            })
            .then(response => {
                if (response.data.status === true){
                    alert(response.data.message)
                    navigate('/result');
                }
                else {
                    alert(response.data.message)
                }
            })
            .catch(error => {
                alert("오류가 발생했습니다.")
            })
        }
    };

    const downloadTemplate = () => {
        const link = document.createElement('a');
        link.href = template;
        link.download = 'OMG_템플릿.pdf';
        link.click();
      };

    return (
        <div className={styles["main"]}>
            <div className={styles["description"]}>
                <p className={styles["title"]}>손글씨 업로드하기</p>
                <div style={{display:"inline-block"}}>1. 템플릿을 다운로드해주세요. </div>
                <DownloadButton handleDownload={downloadTemplate} content="다운로드하기"></DownloadButton>
                <div>2. 다운로드한 템플릿의 칸에 맞게 글씨를 적어주세요.</div>
                <div>3. 80글자 모두 적은 뒤, 이미지로 업로드하면 끝!</div>
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