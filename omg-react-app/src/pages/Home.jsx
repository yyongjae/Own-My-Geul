import React from 'react';
import backgroundImage from '../assets/homeBackgroundImage.png'
import BackgroundImage from "../components/BackgroundImage";
import Button from '../components/Button';
import {Link} from 'react-router-dom';
import styles from "../styles/Home.module.css"

// TODO
// 버튼 클릭 시 #CEC8D8로 색깔 바꾸기
function Home(props) {
    return (
        <div className={styles.home}>
            <BackgroundImage url={backgroundImage}/>
            <div className={styles.description}>
                <div>
                    <span className={styles.title}>OMG</span>
                    <p className={styles.detail}>자신의 손글씨를 폰트로 만들어보세요.</p>
                        <Link to={"/main"}>
                            <Button type="big" content={"폰트 만들기"}></Button>
                        </Link>
                </div>
            </div>
        </div>
    );
}

export default Home;