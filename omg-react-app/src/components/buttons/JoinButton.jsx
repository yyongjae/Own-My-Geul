import React from 'react';
import styles from '../../styles/JoinButton.module.css'

function JoinButton({handleJoin, content}) {
    return (
        <div className={styles["join-div"]}>
            <button onClick={handleJoin} className={styles["join"]}>
                <span className={styles["join-span"]}>{content}</span>
            </button>
        </div>
    )
}

export default JoinButton;