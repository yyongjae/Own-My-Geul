import React from 'react';
import backgroundImage from '../assets/homeBackgroundImage.png'
import BackgroundImage from "../components/BackgroundImage";
import Button from '../components/Button';
import styles from "../styles/Home.module.css"

// TODO
// 버튼 클릭 시 #CEC8D8로 색깔 바꾸기
function Home(props) {
    return (
        <div className={styles.home}>
            <BackgroundImage url={backgroundImage}/>
            <div className={styles.description}>
                <div>
                    <div className={styles.title}>OMG</div>
                    <p className={styles.detail}>자신의 손글씨를 폰트로 만들어보세요.</p>
                    <div className={styles.login_input}>
                        <div className={styles.textForm}>
                            <input name="email" type="text" className={styles.id_input} placeholder="이메일을 입력하세요"></input>
                        </div>
                        <div className={styles.textForm}>
                            <input name="password" type="password" className={styles.pw_input} placeholder="비밀번호를 입력하세요"></input>
                        </div>
                    </div>
                    <Button type="login" content={"로그인"}></Button>
                    <div className={styles.join}>
                        <div>계정이 없으신가요?</div>
                        <Button type="join" content={"회원가입"}></Button>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Home;