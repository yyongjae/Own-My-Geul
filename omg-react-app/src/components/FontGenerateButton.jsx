import React from 'react';
import styles from '../styles/Button.module.css'

function FontGenerateButton({uploadedFile, content, onFileUpload}) {
    return (
        <button disabled={!uploadedFile} className={styles["create"]} onClick={onFileUpload}>
            <span className={styles["create-span"]}>{content}</span>
        </button>
    )
}

export default FontGenerateButton;