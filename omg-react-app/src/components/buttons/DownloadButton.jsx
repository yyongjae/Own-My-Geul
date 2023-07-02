import React from 'react';
import styles from '../../styles/Button.module.css'

function DownloadButton({content, handleDownload}) {
    return (
        <div style={{display:"inline-block"}}>
            <button onClick={handleDownload} className={styles.download}>{content}</button>
        </div>
    );
}

export default DownloadButton;