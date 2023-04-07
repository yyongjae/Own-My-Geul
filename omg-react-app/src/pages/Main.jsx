import React from 'react';
import FontTemplate from '../components/FontTemplate';
import Button from '../components/Button';
import uploadIcon from '../assets/upload.svg';

const MainStyle = {
    padding : "10% 5% 0 5%"
}

const TitleStyle = {
    fontSize : "74px",
    fontWeight : "700"
}

const DescriptionStyle = {
    fontSize : "30px",
    fontWeight : "400"
}

const TitleDescriptionDivLayoutStyle = {
    margin : "10px 0 10px 0"
}

const FontTemplateDivStyle = {
    margin:"0 auto",
    backgroundColor : "#F4EFFF",
    display: "flex",
    justifyContent: "center",
    alignItems: "center"
}


const Main = () => {
    const arr = ["갊", "갸", "곁", "곬", "교", "높", "뉑", "닳", "무", "벚", "숱", "펾"];
    return (
        <div style={MainStyle}>
            <div style={TitleDescriptionDivLayoutStyle}>
                <span style={TitleStyle}>손글씨 업로드하기</span>
                <p style={DescriptionStyle}>아래 12글자를 공책이나 테블릿에서 써서 업로드 해주세요.</p>
            </div>
            
            <div style={FontTemplateDivStyle}>
                <FontTemplate list={arr}/>
            </div>
        </div>
    );
};

export default Main;