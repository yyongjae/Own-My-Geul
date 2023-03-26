import React from 'react';
import styles from "./BackgroundImage.module.css";

function BackgroundImage(props) {
    return (
        <div>
            <img className={styles.image} src={props.url} alt={"woi"}/>
        </div>
    );
}

export default BackgroundImage;