import React from 'react';
import Input from '../components/inputs/Input'
import styles from "../styles/Join.module.css"
import backgroundImage from '../assets/homeBackgroundImage.png'
import BackgroundImage from "../components/BackgroundImage"
import JoinButton from '../components/buttons/JoinButton'
import prevIcon from '../assets/prev.svg'
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';

function Join(props) {
    const navigate = useNavigate();
    const backendServerUrl = process.env.React_APP_BACKEND_SERVER_URL;

    const handleJoin = () => {
        // 이메일과 비밀번호 입력값 가져오기
        const email = document.getElementsByName('email')[0].value;
        const password = document.getElementsByName('password')[0].value;
        const name = document.getElementsByName('name')[0].value;
      
        // 서버로 전송할 데이터 생성
        const data = {
          email: email,
          password: password,
          name: name
        };

        // 서버로 POST 요청 보내기
        axios.post(backendServerUrl + '/api/v1/member/join', data)
        .then(response => {
            // console.log(response.data);
            // 요청이 성공한 경우 처리
            if (response.data.status === true) {
                navigate('/')
            }
            alert(response.data.message)
        })
        .catch(error => {
            // 요청이 실패한 경우 처리
            console.error(error);
        });
    }

    return (
        <div className={styles.join}>
            <BackgroundImage url={backgroundImage}/>
            <div className={styles.form}>
                <div className={styles.prev}>
                    <Link to="/">
                        <img src={prevIcon} alt="oh"/>
                    </Link>
                </div>
                <div className={styles.input}>
                    <div className={styles.title}>회원가입</div>
                    <Input type="email" content="이메일"></Input>
                    <Input type="password" content="비밀번호"></Input>
                    <span id="pw-combination-correct" className={styles.pw_combination_correct}>올바른 비밀번호 입니다.</span>
                    <span id="pw-combination-wrong" className={styles.pw_combination_wrong}>비밀번호는 8자 이상의 영문, 숫자, 특수 문자로 조합해야 합니다. </span>
                    {/* <Input type="password" content="비밀번호 확인"></Input>
                    <span id="pw-correct" className={styles.pw_correct}>비밀번호가 일치합니다.</span>
                    <span id="pw-wrong" className={styles.pw_wrong}>비밀번호가 일치하지 않습니다.</span> */}
                    <Input type="name" content="이름"></Input>
                    <span id="username-empty" className={styles.username_empty}>이름을 입력하세요.</span>
                    <JoinButton handleJoin={handleJoin} content="회원가입"></JoinButton>
                </div>
                {/* <div className={styles.button}>
                    <input onclick="location.href='/login'" class="cancel" type="button" value="취소"/>
                    <input className={styles.join} onclick="joinCheck()" type="submit" value="회원가입"/>
                </div> */}
            </div>
        </div>
    );
}

export default Join;