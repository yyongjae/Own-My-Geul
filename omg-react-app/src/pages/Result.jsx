import React from 'react';
import backgroundImage from '../assets/resultBackgroundImage.png'
import BackgroundImage from "../components/BackgroundImage";
import styles from "../styles/Home.module.css"
import DownloadButton from '../components/DownloadButton';
import {useLocation} from 'react-router-dom';

// TODO
// 버튼 클릭 시 #CEC8D8로 색깔 바꾸기
function Result(props) {
    const location = useLocation();
    const data = location.state.data;
    
    return (
        <div className={styles.home}>
            <BackgroundImage url={backgroundImage}/>
            <div className={styles.description}>
                <div>
                    <span className={styles.title}>완성!</span>
                    <p className={styles.detail}>파일을 다운로드하여 생성된 폰트를 확인해보세요</p>
                    <div>
                        <div style={{display: "flex", justifyContent: "center", alignItems: "center", marginTop:"150px"}}>
                            <DownloadButton type="big" data={data} content={"다운로드"}></DownloadButton>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Result;