import React, {useRef} from 'react'

import styles from '../styles/Button.module.css'

function Button(props) {
    // useRef를 이용해 input태그에 접근한다.
    const imageInput = useRef();

    if (props.type === "big") {
        return (
            <div className={styles["big-div"]}>
                <button className={styles["big"]}>
                    <span className={styles["big-span"]}>{props.content}</span>
                </button>
            </div>
        );
    }
        
    if (props.type === "upload") {
        // 버튼클릭시 input태그에 클릭이벤트를 걸어준다. 
        const onCickImageUpload = () => {
            imageInput.current.click();
        };
        
        // input태그는 display:"none" 을 이용해 안보이게 숨겨준다.
        return (
            <div className={styles["upload-div"]}>
                <input type="file" style={{ display: "none" }} ref={imageInput} />
                <button className={styles["upload"]} onClick={onCickImageUpload}>
                    <img src={props.icon} alt="oh"/>
                    <span className={styles["upload-span"]}>{props.content}</span>
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

    if (props.type === "create") {
        return (
            <button disabled className={styles["create"]}>
                <span className={styles["create-span"]}>{props.content}</span>
            </button>
        )
    }
}

export default Button;