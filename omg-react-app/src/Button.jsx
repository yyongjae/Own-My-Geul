import React from 'react'

const ButtonFontStyle = {
    fontSize : "24px",
    fontWeight : "600",
    opacity : "65%"
}

const ButtonDiv = {
    marginTop : "186px",
    marginLeft : "20px"
}


const ButtonStyle = {
    width : "324px",
    height : "99px",
    borderRadius : "30px",
    boxShadow : "0px 4px 4px rgba(0,0,0,0.25)",
    border : "none",
    backgroundColor : "#F4EFFF"
}

function Button(props) {
    return (
        <div style={ButtonDiv}>
            <button style={ButtonStyle} onClick={props.goMain}>
                <span style={ButtonFontStyle}>{props.content}</span>
            </button>
        </div>
    );
}

export default Button;