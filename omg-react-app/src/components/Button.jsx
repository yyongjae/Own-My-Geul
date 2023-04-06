import React from 'react'

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
}

export default Button;