import React from 'react';
import { useNavigate } from 'react-router-dom';
import styles from '../../styles/Button.module.css'

function LoginButton(props) {

    const navigate = useNavigate();

    const goToMain = () => {
        navigate('/main')
    };

    return (
        <div className={styles["login-div"]}>
            <button onClick={goToMain} className={styles["login"]}>
                <span className={styles["login-span"]}>{props.content}</span>
            </button>
        </div>
    )
}

export default LoginButton;