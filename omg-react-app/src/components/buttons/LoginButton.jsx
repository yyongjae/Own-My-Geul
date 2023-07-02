import React from 'react';
import styles from '../../styles/Button.module.css'

function LoginButton({handleLogin, content}) {
    return (
        <div className={styles["login-div"]}>
            <button onClick={handleLogin} className={styles["login"]}>
                <span className={styles["login-span"]}>{content}</span>
            </button>
        </div>
    )
}

export default LoginButton;