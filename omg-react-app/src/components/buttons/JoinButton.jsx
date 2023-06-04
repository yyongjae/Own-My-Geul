import React from 'react';
import styles from '../../styles/JoinButton.module.css'

function JoinButton(props) {
    return (
        <div className={styles["join-div"]}>
            <button className={styles["join"]}>
                <span className={styles["join-span"]}>{props.content}</span>
            </button>
        </div>
    )
}

export default JoinButton;