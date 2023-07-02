import React from 'react';
import backgroundImage from '../assets/resultBackgroundImage.png'
import BackgroundImage from "../components/BackgroundImage";
import styles from "../styles/Home.module.css"

// TODO
// 버튼 클릭 시 #CEC8D8로 색깔 바꾸기
function Result(props) {
    return (
        <div className={styles.home}>
            <BackgroundImage url={backgroundImage}/>
            <div className={styles.description}>
                <div>
                    <span className={styles.title}>완성!</span>
                    <p className={styles.detail}>완성된 폰트는 회원님의 이메일로 전송해드리겠습니다.</p>
                </div>
            </div>
        </div>
    );
}

export default Result;