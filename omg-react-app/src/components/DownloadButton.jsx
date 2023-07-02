import React from 'react';
import styles from '../styles/Button.module.css'

function DownloadButton(props) {
    const downloadFile = () => {
        const url = window.URL.createObjectURL(props.data);
        const link = document.createElement('a');
        link.href = url;
        link.download = 'output.ttf';
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
        URL.revokeObjectURL(url);
    };

    return (
        <div>
            <button onClick={downloadFile} className={styles["big"]}>
                <span className={styles["big-span"]}>{props.content}</span>
            </button>
        </div>
    );
}

export default DownloadButton;