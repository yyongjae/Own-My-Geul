import React from 'react';
import styles from '../../styles/Button.module.css'
import { useNavigate } from 'react-router-dom';

function StartButton({content}) {
    const navigate = useNavigate();

    function goToMain() {
        navigate('/main')
    }

    return (
        <div className={styles["start-div"]}>
            <button onClick={goToMain} className={styles["start"]}>
                <span className={styles["start-span"]}>{content}</span>
            </button>
        </div>
    );
}

export default StartButton;