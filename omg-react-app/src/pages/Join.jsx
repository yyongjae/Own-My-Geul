import React from 'react';
import Input from '../components/inputs/Input'
import styles from "../styles/Join.module.css"
import backgroundImage from '../assets/homeBackgroundImage.png'
import BackgroundImage from "../components/BackgroundImage"
import JoinButton from '../components/buttons/JoinButton'
import prevIcon from '../assets/prev.svg'
import { Link } from 'react-router-dom';

function Join(props) {
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
                    <Input type="password" content="비밀번호 확인"></Input>
                    <span id="pw-correct" className={styles.pw_correct}>비밀번호가 일치합니다.</span>
                    <span id="pw-wrong" className={styles.pw_wrong}>비밀번호가 일치하지 않습니다.</span>
                    <Input type="name" content="이름"></Input>
                    <span id="username-empty" className={styles.username_empty}>이름을 입력하세요.</span>
                    <JoinButton content="회원가입"></JoinButton>
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