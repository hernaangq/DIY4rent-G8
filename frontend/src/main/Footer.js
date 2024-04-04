import React from 'react';
import PropTypes from 'prop-types';
import { MDBFooter, MDBContainer, MDBRow, MDBCol, MDBIcon } from 'mdb-react-ui-kit';
import styled from 'styled-components';
import { makeStyles } from '@mui/styles';

const useStyles = makeStyles({
  waves: {
    position: "relative",
    width: "100%",
    marginBottom: -7,
    height: "7vw",
    minHeight: "7vw"
  },
  "@keyframes moveForever": {
    from: { transform: "translate3d(-90px, 0, 0)" },
    to: { transform: "translate3d(85px, 0, 0)" }
  },
  parallax: {
    "& > use": {
      animation: "$moveForever 4s cubic-bezier(0.62, 0.5, 0.38, 0.5) infinite",
      animationDelay: props => `-${props.animationNegativeDelay}s`
    }
  }
});

const WaveBorderContainer = styled.div`
  position: relative;
  background: ${(props) => props.upperColor};
`;

const SVGWave = styled.svg`
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  min-height: ${(props) => props.minHeight};
`;

const Content = styled.div`
  position: relative;
  z-index: 1;
  color: white;
  text-align: center;
  padding: 30px 0;
`;

const Section = styled.div`
  margin-bottom: 20px;
`;

function WaveBorder(props) {
  const classes = useStyles(props);
  const id = String(Math.random());
  const {
    className,
    lowerColor,
    animationNegativeDelay,
    ...rest
  } = props;
  return (
    <WaveBorderContainer className={className} upperColor={props.upperColor} {...rest}>
      <SVGWave
        className={classes.waves}
        xmlns="http://www.w3.org/2000/svg"
        xlink="http://www.w3.org/1999/xlink"
        viewBox="0 24 150 28"
        preserveAspectRatio="none"
        shapeRendering="auto"
        minHeight="100px" // Ajusta esta altura según tu necesidad
      >
        <defs>
          <path
            id={id}
            d="M-160 44c30 0 58-18 88-18s 58 18 88 18 58-18 88-18 58 18 88 18 v44h-352z"
          />
        </defs>
        <g className={classes.parallax}>
          <use href={`#${id}`} x="48" y="0" fill={lowerColor} />
        </g>
      </SVGWave>
    </WaveBorderContainer>
  );
}

WaveBorder.propTypes = {
  lowerColor: PropTypes.string.isRequired,
  upperColor: PropTypes.string.isRequired,
  animationNegativeDelay: PropTypes.number.isRequired
};

function Footer(props) {
    const { lowerColor, upperColor, animationNegativeDelay } = props;
    return (
      <WaveBorder lowerColor={lowerColor} upperColor={upperColor} animationNegativeDelay={animationNegativeDelay}>
        <MDBFooter style={{ backgroundColor: 'light', position: 'absolute', bottom: '0', width: '100%' }} className='text-center text-lg-start text-muted'>
        <Content>
          <Section>
            <h5>Conecta con nuestra redes sociales:</h5>
            <div>
              <a href='' className='me-4 text-reset'>
                <MDBIcon fab icon="facebook-f" />
              </a>
              <a href='' className='me-4 text-reset'>
                <MDBIcon fab icon="twitter" />
              </a>
              <a href='' className='me-4 text-reset'>
                <MDBIcon fab icon="google" />
              </a>
              <a href='' className='me-4 text-reset'>
                <MDBIcon fab icon="instagram" />
              </a>
              <a href='' className='me-4 text-reset'>
                <MDBIcon fab icon="linkedin" />
              </a>
              <a href='' className='me-4 text-reset'>
                <MDBIcon fab icon="github" />
              </a>
            </div>
          </Section>
          <Section>
            <MDBContainer className='text-center text-md-start mt-5'>
              <MDBRow className='mt-3'>
                <MDBCol md="3" lg="4" xl="3" className='mx-auto mb-4'>
                  <h6 className='text-uppercase fw-bold mb-4'>
                    <MDBIcon icon="gem" className="me-3" />
                    DIY4Rent
                  </h6>
                </MDBCol>
      
                <MDBCol md="3" lg="2" xl="2" className='mx-auto mb-4'>
                  <h6 className='text-uppercase fw-bold mb-4'>Links de uso:</h6>
                  <p>
                    <a href='#!' className='text-reset'>
                      Precio
                    </a>
                  </p>
                  <p>
                    <a href='#!' className='text-reset'>
                      Ajustes
                    </a>
                  </p>
                  <p>
                    <a href='#!' className='text-reset'>
                      Perfil
                    </a>
                  </p>
                  <p>
                    <a href='#!' className='text-reset'>
                      Ayuda
                    </a>
                  </p>
                </MDBCol>
      
                <MDBCol md="4" lg="3" xl="3" className='mx-auto mb-md-0 mb-4'>
                  <h6 className='text-uppercase fw-bold mb-4'>Contact</h6>
                  <p>
                    <MDBIcon icon="home" className="me-2" />
                    Madrid, España
                  </p>
                  <p>
                    <MDBIcon icon="envelope" className="me-3" />
                    diy4@rent.com
                  </p>
                  <p>
                    <MDBIcon icon="phone" className="me-3" /> + 34 999 999 999
                  </p>
                </MDBCol>
              </MDBRow>
            </MDBContainer>
          </Section>
          <div className='text-center p-4' style={{ backgroundColor: 'rgba(0, 0, 0, 0.05)' }}>
            © 2023 Copyright:
            <a className='text-reset fw-bold' href='https://mdbootstrap.com/'>
              MDBootstrap.com
            </a>
          </div>
        </Content>
      </MDBFooter>
    </WaveBorder>
  );
}

Footer.propTypes = {
  lowerColor: PropTypes.string.isRequired,
  upperColor: PropTypes.string.isRequired,
  animationNegativeDelay: PropTypes.number.isRequired
};

export default Footer;
