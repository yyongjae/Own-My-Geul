import React from 'react';


const FontDivStyle = {
    fontSize: "90px",
    padding: "10px",
    margin: "10px 0 10px 0"
}

const FontTemplate = (props) => {
    const arr = props.list
    return (
        <div style={FontDivStyle}>
        {
            arr.map((elem) => { 
                return (
                    <span style={{padding: "12px"}}>{elem}</span>
                )
            })
        }
        </div>
    );
};

export default FontTemplate;