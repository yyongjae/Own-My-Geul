import React from 'react';
import { useNavigate } from 'react-router-dom';

import styles from '../styles/Button.module.css'

function Button(props) {
    const navigate = useNavigate();

    const goToMain = () => {
        navigate('/main')
    };

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

    if (props.type === "login") {

        return (
            <div className={styles["login-div"]}>
                <button onClick={goToMain} className={styles["login"]}>
                    <span className={styles["login-span"]}>{props.content}</span>
                </button>
            </div>
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