import React, {useRef} from 'react'
import { useState } from 'react';

import styles from '../styles/Button.module.css'

function UploadButton({onFileUpload, content, icon}) {
    const [file, setFile] = useState();
    const handleFileChange = (event) => {
        const selectedFile = event.target.files[0];
        setFile(selectedFile);
        onFileUpload(selectedFile);
    };

    // useRef를 이용해 input태그에 접근한다.
    const imageInput = useRef();

    // 버튼클릭시 input태그에 클릭이벤트를 걸어준다. 
    const onCickImageUpload = () => {
        imageInput.current.click();
    };
    
    // input태그는 display:"none" 을 이용해 안보이게 숨겨준다.
    return (
        <div className={styles["upload-div"]}>
            <input type="file" style={{ display: "none" }} ref={imageInput} onChange={handleFileChange}/>
            <button className={styles["upload"]} onClick={onCickImageUpload}>
                <img src={icon} alt="oh"/>
                <span className={styles["upload-span"]}>{content}</span>
            </button>
            <span className={styles["upload-file-span"]}>{file ? file.name : ''}</span>
        </div>
    );
}

export default UploadButton;