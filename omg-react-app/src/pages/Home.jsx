import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import backgroundImage from '../assets/homeBackgroundImage.png'
import BackgroundImage from "../components/BackgroundImage";
import Button from '../components/Button';
import LoginButton from '../components/buttons/LoginButton';
import styles from "../styles/Home.module.css"
import axios from 'axios';

// TODO
// 버튼 클릭 시 #CEC8D8로 색깔 바꾸기
function Home(props) {
    const navigate = useNavigate();
    const backendServerUrl = process.env.React_APP_BACKEND_SERVER_URL;
    const handleLogin = () => {
        // 이메일과 비밀번호 입력값 가져오기
        const email = document.getElementsByName('email')[0].value;
        const password = document.getElementsByName('password')[0].value;
      
        // 서버로 전송할 데이터 생성
        const data = {
          email: email,
          password: password
        };

        // 서버로 POST 요청 보내기
        axios.post(backendServerUrl + '/api/v1/member/login', data)
        .then(response => {
            console.log(response.data);
            // 요청이 성공한 경우 처리
            if (response.data.status === true) {
                const memberInfo = response.data.data.memberInfo
                const tokenInfo = response.data.data.tokenInfo
                const accessToken = tokenInfo.grantType + " " + tokenInfo.accessToken

                localStorage.setItem("name", memberInfo.name)
                localStorage.setItem("accessToken", accessToken)
                navigate('/main')
            }
            alert(response.data.message)
        })
        .catch(error => {
            // 요청이 실패한 경우 처리
            console.error(error);
        });
    }

    return (
        <div className={styles.home}>
            <BackgroundImage url={backgroundImage}/>
            <div className={styles.description}>
                <div>
                    <div className={styles.title}>OMG</div>
                    <p className={styles.detail}>자신의 손글씨를 폰트로 만들어보세요.</p>
                    <div className={styles.login_input}>
                        <div className={styles.textForm}>
                            <input  name="email" type="email" className={styles.id_input} placeholder="이메일을 입력하세요"></input>
                        </div>
                        <div className={styles.textForm}>
                            <input name="password" type="password" className={styles.pw_input} placeholder="비밀번호를 입력하세요"></input>
                        </div>
                    </div>
                    <LoginButton handleLogin={handleLogin} content={"로그인"}></LoginButton>
                    <div className={styles.join}>
                        <div>계정이 없으신가요?</div>
                        <Link to="/join">
                            <Button type="join" content={"회원가입"}></Button>
                        </Link>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Home;