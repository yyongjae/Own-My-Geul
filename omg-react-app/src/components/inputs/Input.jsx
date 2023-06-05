import React from 'react';
import styles from "../../styles/Input.module.css"

function Input(props) {
    if (props.type === "email") {
        return (
            <div className={styles.field}>
                <b>{props.content}</b>
                <input name="email" type="text"/>
            </div>
        );
    }

    if (props.type === "password") {
        return (
            <div className={styles.field}>
                <b>{props.content}</b>
                <input name="password" type="password"/>
            </div>
        );
    }

    if (props.type === "name") {
        return (
            <div className={styles.field}>
                <b>{props.content}</b>
                <input name="name" type="text"/>
            </div>
        );
    }
}

export default Input;