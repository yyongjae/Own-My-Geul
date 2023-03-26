import React from 'react';
import backgroundImage from './homeBackgroundImage.png'
import BackgroundImage from "./BackgroundImage";

import styles from "./Home.module.css"

import {useState} from "react";



const OMGFontStyle = {
    fontSize : "128px",
    fontWeight : "600"
}

const DescriptionFontStyle = {
    fontSize : "24px",
    fontWeight : "400",
    paddingLeft : "10px"
}

const ButtonFontStyle = {
    fontSize : "24px",
    fontWeight : "600",
    opacity : "65%"
}

const Button = {
    width : "324px",
    height : "99px",
    borderRadius : "30px",
    boxShadow : "0px 4px 4px rgba(0,0,0,0.25)",
    border : "none",
    backgroundColor : "#F4EFFF"
}

const ButtonDiv = {
    marginTop : "186px",
    marginLeft : "20px"
}
// TODO
// 버튼 클릭 시 #CEC8D8로 색깔 바꾸기
function Home(props) {
    return (
        <div className={styles.components}>
            <BackgroundImage url={backgroundImage}/>
            <div className={styles.description}>
                <div>
                    <span style={OMGFontStyle}>OMG</span>
                    <p style={DescriptionFontStyle}>자신의 손글씨를 폰트로 만들어보세요.</p>
                    <div style={ButtonDiv}>
                        <button style={Button}>
                            <span style={ButtonFontStyle}>폰트 만들기</span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
}
export default Home;