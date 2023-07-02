import React from 'react';

import styles from '../styles/Button.module.css'

function Button(props) {

    if (props.type === "big") {
        return (
            <div className={styles["big-div"]}>
                <button className={styles["big"]}>
                    <span className={styles["big-span"]}>{props.content}</span>
                </button>
            </div>
        );
    }

    if (props.type === "prev") {
        return (
            <button className={styles["prev"]}>
                <span className={styles["prev-span"]}>{props.content}</span>
            </button>
        )
    }

    if (props.type === "join") {
        return (
            <button className={styles["join"]}>
                회원가입
            </button>
        )
    }
}

export default Button;